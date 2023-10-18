/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package neo4j_familytree;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Record;
import org.neo4j.driver.Values;

/**
 *
 * @author COHOTECH
 */
public class addRela extends javax.swing.JFrame {
    private String name1;
    private String name2;
    private String relation;
    

    /**
     * Creates new form addRela
     */
    public addRela() {
        setTitle("Mối quan hệ");
        initComponents();
        setLocationRelativeTo(null);
        loadRelationshipNames();
        name1 = hoten1.getText().toString();
        name2 = hoten2.getText().toString();
        relation = rela.getSelectedItem().toString();
    }
    private void loadRelationshipNames() {
       try (Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "Phat121002@"))) {
            try (Session session = driver.session()) {
                String query = "MATCH (n:Information)-[r:Has_Relation]->(m:Information) RETURN DISTINCT r.relation as relationship";
            Result result = session.run(query);

            // Lấy danh sách tên rela từ kết quả truy vấn
            java.util.List<String> relationshipList = new ArrayList<>();
            while (result.hasNext()) {
                Record record = result.next();
                relationshipList.add(record.get("relationship").asString());
            }
               

            // Thêm tên rela vào jComboBox1
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(relationshipList.toArray(new String[0]));
            rela.setModel(model);
               
            }
        }

        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        hoten2 = new javax.swing.JTextField();
        hoten1 = new javax.swing.JTextField();
        btnAddRela = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        rela = new javax.swing.JComboBox<>();
        btnxoaRela = new javax.swing.JButton();
        btnsuaRela = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setBackground(new java.awt.Color(255, 51, 51));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("MỐI QUAN HỆ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Họ tên người 2");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Họ tên người 1");

        hoten2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        hoten2.setName(""); // NOI18N

        hoten1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        hoten1.setName(""); // NOI18N

        btnAddRela.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnAddRela.setText("Thêm Relation");
        btnAddRela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRelaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Mối quan hệ");

        rela.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rela.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnxoaRela.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnxoaRela.setText("Xóa Relation");
        btnxoaRela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaRelaActionPerformed(evt);
            }
        });

        btnsuaRela.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnsuaRela.setText("Sửa Relation");
        btnsuaRela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaRelaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hoten1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hoten2, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 109, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(rela, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(95, 95, 95))
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btnAddRela)
                .addGap(79, 79, 79)
                .addComponent(btnsuaRela)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnxoaRela)
                .addGap(19, 19, 19))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(172, 172, 172))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(hoten1))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(hoten2))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rela, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddRela)
                    .addComponent(btnxoaRela)
                    .addComponent(btnsuaRela))
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddRelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRelaActionPerformed
        // TODO add your handling code here:
        try (Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "Phat121002@"))) {
        try (Session session = driver.session()) {
            String query = "MATCH (n:Information {name: $name1}), (m:Information {name: $name2}) " +
                           "CREATE (n)-[:Has_Relation {relation: $relation}]->(m)";
            session.run(query, Values.parameters("name1", name1, "name2", name2, "relation", relation));
            JOptionPane.showMessageDialog(null, "Thêm mối quan hệ thành công");
        }
         catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
         }
    }
        

    }//GEN-LAST:event_btnAddRelaActionPerformed

    private void btnxoaRelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaRelaActionPerformed
        // TODO add your handling code here:
        try (Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "Phat121002@"))) {
        try (Session session = driver.session()) {
            String query =  "MATCH (n:Information {name: $name1})-[r:Has_Relation]-(m:Information {name: $name2}) " +
                            "DETACHE DELETE r";
            session.run(query, Values.parameters("name1", name1, "name2", name2));
            JOptionPane.showMessageDialog(null, "Xóa mối quan hệ thành công");
        }
         catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
          }
       }        
        
    }//GEN-LAST:event_btnxoaRelaActionPerformed

    private void btnsuaRelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaRelaActionPerformed
        // TODO add your handling code here:
        try (Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "Phat121002@"))) {
        try (Session session = driver.session()) {
            String query =  "MATCH (n:Information {name: $name1})-[r:Has_Relation]-(m:Information {name: $name2}) " +
                            "DETACHE DELETE r";
            session.run(query, Values.parameters("name1", name1, "name2", name2));
            JOptionPane.showMessageDialog(null, "Xóa mối quan hệ thành công");
        }
         catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
          }
       } 
    }//GEN-LAST:event_btnsuaRelaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(addRela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addRela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addRela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addRela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addRela().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddRela;
    private javax.swing.JButton btnsuaRela;
    private javax.swing.JButton btnxoaRela;
    private javax.swing.JTextField hoten1;
    private javax.swing.JTextField hoten2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JComboBox<String> rela;
    // End of variables declaration//GEN-END:variables
}
