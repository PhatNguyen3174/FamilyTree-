package neo4j_familytree;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxICell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import org.neo4j.driver.*;

import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Relationship;
import org.neo4j.driver.Record;
public class FamilyTreeApp extends JApplet {
    private static final Dimension DEFAULT_SIZE = new Dimension(1200, 800);
    private JGraphXAdapter<String, DefaultEdge> jgxAdapter;
    private String name ;
    private String sex;
    private String dateOfBirth;
    private String phoneNumber;
    private String placeOfBirth;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FamilyTreeApp applet = new FamilyTreeApp();
            applet.init();

            JFrame frame = new JFrame();
            frame.getContentPane().add(applet);
            frame.setTitle("Family Tree Visualization");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
            
            JButton showInfoButton = new JButton("Thêm");
            showInfoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Handle button click, you can show information about the selected person here
                    addInfor app = new addInfor();
                    app.setVisible(true);
                    
                }
            });
            
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(showInfoButton);
            frame.add(buttonPanel, BorderLayout.SOUTH);

            frame.pack();
            frame.setVisible(true);
        });
    }

    class CustomEdge extends DefaultEdge {
        private String relationshipName;

        public CustomEdge(String relationshipName) {
            this.relationshipName = relationshipName;
        }

        public String getRelationshipName() {
            return relationshipName;
        }

        @Override
        public String toString() {
            return relationshipName;
        }
    }

    @Override
    public void init() {
        try (Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "Phat121002@"))) {
            ListenableGraph<String, DefaultEdge> g = new DefaultListenableGraph<>(new DefaultDirectedGraph<>(DefaultEdge.class));

            // Create a hierarchical layout with vertical orientation
            jgxAdapter = new JGraphXAdapter<>(g);
            setPreferredSize(DEFAULT_SIZE);
            mxGraphComponent component = new mxGraphComponent(jgxAdapter);
            component.setConnectable(false);
            component.getGraph().setAllowDanglingEdges(false);
            getContentPane().add(component);
            resize(DEFAULT_SIZE);
            
            component.getGraphControl().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1) {
                        // Lấy cell đang được chọn
                        Object cell = component.getCellAt(e.getX(), e.getY());

                        if (cell instanceof mxICell) {
                            mxICell mxCell = (mxICell) cell;
                            if (mxCell.isVertex()) {
                                // Lấy tên của node
                                String nodeName = mxCell.getValue().toString();

                                // Thực hiện truy vấn và hiển thị thông tin
                                String info = queryNeo4j(nodeName);
                                Information app = new Information(info);
                                app.updateInformation(name, placeOfBirth, phoneNumber, sex);
                                app.setVisible(true);
                                
                                
                            }
                        }
                    }
                }
            });

            try (Session session = driver.session()) {
                String query = "match(n:Information)-[r]->(m:Information) RETURN n, r, m";
                Result result = session.run(query);

                while (result.hasNext()) {
                    Record record = result.next();
                    Node node1 = record.get("n").asNode();
                    Node node2 = record.get("m").asNode();
                    Relationship relationship = record.get("r").asRelationship();
                    String relationshipName = relationship.get("relation").asString();
                    String node3 = node1.get("name").asString();
                    String node4 = node2.get("name").asString();

                    g.addVertex(node3);
                    g.addVertex(node4);
                    CustomEdge edge = new CustomEdge(relationshipName);
                    g.addEdge(node3, node4, edge);
                }
            }

            // Use hierarchical layout with vertical orientation
            mxHierarchicalLayout layout = new mxHierarchicalLayout(jgxAdapter);
            layout.setIntraCellSpacing(50);
            layout.setInterRankCellSpacing(100);
            layout.setOrientation(SwingConstants.NORTH);
            layout.execute(jgxAdapter.getDefaultParent());

            // Adjust the size and font of nodes
            Map<String, Object> style = new HashMap<>();
            style.put(mxConstants.STYLE_FONTSIZE, 30);
            style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
            jgxAdapter.getStylesheet().putCellStyle("NODE_STYLE", style);

            for (Object cell : jgxAdapter.getChildCells(jgxAdapter.getDefaultParent(), true, false)) {
                if (cell instanceof mxICell) {
                    mxICell mxCell = (mxICell) cell;
                    if (mxCell.isVertex()) {
                        mxCell.setStyle("NODE_STYLE");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Phương thức để thực hiện truy vấn Neo4j và trả về tên người đó
    private String queryNeo4j(String nodeName) {
    try (Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "Phat121002@"))) {
        try (Session session = driver.session()) {
            String query = "MATCH (person:Information) WHERE person.name = $name " +
                           "RETURN person.name AS name, person.sex AS sex, " +
                           "person.dateOfBirth AS dateOfBirth, person.phoneNumber AS phoneNumber, " +
                           "person.placeOfBirth AS placeOfBirth";
            Result result = session.run(query, Values.parameters("name", nodeName));

            if (result.hasNext()) {
                Record record = result.next();
                 name = record.get("name").asString();
                 sex = record.get("sex").asString();
                 dateOfBirth = record.get("dateOfBirth").asLocalDate().toString();
                 phoneNumber = record.get("phoneNumber").asString();
                 placeOfBirth = record.get("placeOfBirth").asString();
            } else {
                return "Person not found!";
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "Error while querying Neo4j.";
    }
        return null;
}


    
}
