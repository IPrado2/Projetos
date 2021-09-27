package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import util.Mascara;


public class TelaPrincipal extends javax.swing.JFrame {

    private String painel;
    private int tabelaCont = 0;
    private int vaga1 = 0;
    private int vaga2 = 0;
    private int vaga3 = 0;
    private int vaga4 = 0;
    private int vaga5 = 0;
    private Mascara mc = new Mascara();
    private Calendar calendar = Calendar.getInstance();
    
    
    DefaultTableModel tm = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int linhas, int colunas){
            return false;}};
    
    
    public TelaPrincipal() {
        initComponents();
        mostraPainel("Cadastro");
        setLocationRelativeTo(null);
        setTitle("Sistema de Cadastro de Candidatos");
        tabela.setModel(tm);
        criarColuna(tm);
        setMask();        
    }

    public void setMask(){
        tfDtnasc.setFormatterFactory(mc.getDataMask());
        tfCPF.setFormatterFactory(mc.getCPFMask());
    }
    
    private void limpaTelaCandidato() {
        tfNome.setText("");
        tfCPF.setText("");
        tfSobrenome.setText("");
        tfDtnasc.setText("");
    }
     
    private DefaultTableModel criarColuna(DefaultTableModel tm){
        tm.addColumn("CPF");
        tm.addColumn("Nome");
        tm.addColumn("Sobrenome");
        tm.addColumn("Data de nascimento");
        tm.addColumn("Idade");
        tm.addColumn("É maior de idade?");
        return tm;
    }
    
    
    private boolean verificarCampoVazio(JTextField verifica){
        if(verifica.getText().trim().isEmpty())   
    {   
        JOptionPane.showMessageDialog(null,"Campo "+ verifica.getName()+" não pode ficar vazio! A pessoa não foi adicionada a lista.","Atenção!",JOptionPane.INFORMATION_MESSAGE);   
        verifica.requestFocus();
        verifica.setBackground(Color.red);
        vagaFull();
        return true;
    }
        verifica.setBackground(Color.white);
        return false;
}
    private int calculaIdade(String dtnasc){
        int idade = calendar.getWeekYear() - Integer.parseInt(dtnasc.substring(dtnasc.length() - 4)); 
        return idade;
    }
    
    private String calculaMaior(int idade){
        String maior;
        if (idade >= 18) {
            maior = "Sim";
        } else maior = "Não";
        return maior;
    }
    
    private boolean escolheVaga(){
        boolean add = false;
        int escolha = (Integer) spVagas.getValue();
        if(tabelaCont <= 9){
        if (escolha == 1){
                if(vaga1 < 3){
                    vaga1 = vaga1 + 1;
                    add = true;
                } else {
                   JOptionPane.showMessageDialog(null,"Já foram cadastrados 3 candidatos para essa vaga!","Atenção!",JOptionPane.INFORMATION_MESSAGE);}}
        if (escolha ==2){
                if(vaga2 < 3){
                    vaga2 = vaga2 + 1;
                    add = true;
                } else {
                   JOptionPane.showMessageDialog(null,"Já foram cadastrados 3 candidatos para essa vaga!","Atenção!",JOptionPane.INFORMATION_MESSAGE);}}
        if (escolha == 3){
                if(vaga3 < 3){
                    vaga3 = vaga3 + 1;
                    add = true;
                } else {
                   JOptionPane.showMessageDialog(null,"Já foram cadastrados 3 candidatos para essa vaga!","Atenção!",JOptionPane.INFORMATION_MESSAGE); 
                }}
       if (escolha == 4){
                if(vaga4 < 3){
                    vaga4 = vaga4 + 1;
                    add = true;
                } else {
                   JOptionPane.showMessageDialog(null,"Já foram cadastrados 3 candidatos para essa vaga!","Atenção!",JOptionPane.INFORMATION_MESSAGE);}}
        if (escolha == 5){
                if(vaga5 < 3){
                    vaga5 = vaga5 + 1;
                    add = true;
                } else {
                   JOptionPane.showMessageDialog(null,"Já foram cadastrados 3 candidatos para essa vaga!","Atenção!",JOptionPane.INFORMATION_MESSAGE);}}
        } else {
        JOptionPane.showMessageDialog(null,"Impossível cadastrar! O número de candidatos já atingiu o limite permitido!","Atenção!",JOptionPane.INFORMATION_MESSAGE);
        }  
        return add;
    }
    
    private void vagaFull(){
        int vg = (Integer) spVagas.getValue();
                if (vg == 1){
                    vaga1 = vaga1 - 1;}
                if (vg == 2){
                    vaga2 = vaga2 - 1;}
                if (vg == 3){
                    vaga3 = vaga3 - 1;}
                if (vg == 4){
                    vaga4 = vaga4 - 1;}
                if (vg == 5){
                    vaga5 = vaga5 - 1;}
    }
    
    private String[] verificaCPF(String[] item, String cpf){ 
        boolean repetido = false;
        
        if (tm.getRowCount() > 0){ 
            for(int i = 0; i < tm.getRowCount(); i++){
            if (cpf.equals(tm.getValueAt(i, 0))){
                repetido = true;
            }} 
            if (repetido == true){
                JOptionPane.showMessageDialog(null,"CPF já cadastrado ou incorreto!","Atenção!",JOptionPane.INFORMATION_MESSAGE);
                vagaFull();
            } else {
                tm.addRow(item);
                tabelaCont = tabelaCont + 1;
               
            }} else {
                tm.addRow(item);    
                tabelaCont = tabelaCont + 1;}
       
        return item;
    }
    
    
    private void Addlista(){

        if (tabelaCont <= 9){
        String nome = tfNome.getText();
        String sobrenome = tfSobrenome.getText();
        String cpf = tfCPF.getText().replaceAll("___.___.___-__", "");
        String dtnasc = tfDtnasc.getText().replaceAll("__/__/____","");
        
        
        if (cpf.equals("")){
           JOptionPane.showMessageDialog(null, "Por favor informe o CPF","Atenção!",JOptionPane.INFORMATION_MESSAGE);
           tfCPF.setBackground(Color.red);
            vagaFull();}
        else {
        if (dtnasc.equals("")){
           JOptionPane.showMessageDialog(null, "Por favor preencha a data de nascimento","Atenção!",JOptionPane.INFORMATION_MESSAGE);
           tfDtnasc.setBackground(Color.red);
           vagaFull();
        } else {
            int idade = calculaIdade(dtnasc);       
            String maior = calculaMaior(idade);
            tfDtnasc.setBackground(Color.white);
            if (verificarCampoVazio(tfNome) || verificarCampoVazio(tfCPF) || verificarCampoVazio(tfSobrenome)){
                  
        } else {
            tfDtnasc.setBackground(Color.white);    
            Cliente cd = new Cliente(cpf, nome, sobrenome, dtnasc, idade, maior);
            String [] item = new String[] {cd.getCpf(), cd.getNome(), cd.getSobrenome(),cd.getDtnasc(),String.valueOf(cd.getIdade()), cd.getEmaior()};
            verificaCPF(item, cpf);
            limpaTelaCandidato();}    
        }}} else {
        JOptionPane.showMessageDialog(null,"Impossível cadastrar! O número de candidatos já atingiu o limite permitido!","Atenção!",JOptionPane.INFORMATION_MESSAGE); 
        }
    }
    
    private void mostraPainel(String pn) {
        CardLayout cl = (CardLayout) (pnCentral.getLayout());
        cl.show(pnCentral, pn);
        painel = pn;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnTitulo = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JLabel();
        pnMenu = new javax.swing.JPanel();
        lbEspaco0 = new javax.swing.JLabel();
        btCadastro = new javax.swing.JButton();
        lbEspaco1 = new javax.swing.JLabel();
        btClientes = new javax.swing.JButton();
        pnCentral = new javax.swing.JPanel();
        pnCadastro = new javax.swing.JPanel();
        lbNome = new javax.swing.JLabel();
        lbiCPF = new javax.swing.JLabel();
        lbSobrenome = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        tfSobrenome = new javax.swing.JTextField();
        lbDtnasc = new javax.swing.JLabel();
        btAdd = new javax.swing.JButton();
        tfDtnasc = new javax.swing.JFormattedTextField();
        tfCPF = new javax.swing.JFormattedTextField();
        spVagas = new javax.swing.JSpinner();
        lbEscolheVaga = new javax.swing.JLabel();
        pnCandidatos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        btSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnTitulo.setBackground(new java.awt.Color(37, 164, 212));
        pnTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnTitulo.setMinimumSize(new java.awt.Dimension(760, 80));
        pnTitulo.setPreferredSize(new java.awt.Dimension(744, 80));
        pnTitulo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbTitulo.setFont(new java.awt.Font("OCR A Extended", 1, 36)); // NOI18N
        lbTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lbTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo.setText("Cadastrar novo candidato");
        pnTitulo.add(lbTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 730, 60));

        getContentPane().add(pnTitulo, java.awt.BorderLayout.NORTH);

        pnMenu.setBackground(new java.awt.Color(3, 30, 82));
        pnMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnMenu.setLayout(new javax.swing.BoxLayout(pnMenu, javax.swing.BoxLayout.PAGE_AXIS));

        lbEspaco0.setMaximumSize(new java.awt.Dimension(34, 25));
        lbEspaco0.setMinimumSize(new java.awt.Dimension(34, 25));
        lbEspaco0.setPreferredSize(new java.awt.Dimension(34, 25));
        pnMenu.add(lbEspaco0);

        btCadastro.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        btCadastro.setForeground(new java.awt.Color(255, 255, 255));
        btCadastro.setText("Novo Cadastro");
        btCadastro.setBorderPainted(false);
        btCadastro.setContentAreaFilled(false);
        btCadastro.setMaximumSize(new java.awt.Dimension(143, 38));
        btCadastro.setMinimumSize(new java.awt.Dimension(145, 45));
        btCadastro.setPreferredSize(new java.awt.Dimension(145, 45));
        btCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadastroActionPerformed(evt);
            }
        });
        pnMenu.add(btCadastro);

        lbEspaco1.setMaximumSize(new java.awt.Dimension(34, 25));
        lbEspaco1.setMinimumSize(new java.awt.Dimension(34, 25));
        lbEspaco1.setPreferredSize(new java.awt.Dimension(34, 25));
        pnMenu.add(lbEspaco1);

        btClientes.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        btClientes.setForeground(new java.awt.Color(255, 255, 255));
        btClientes.setText("Candidatos");
        btClientes.setBorderPainted(false);
        btClientes.setContentAreaFilled(false);
        btClientes.setMaximumSize(new java.awt.Dimension(143, 38));
        btClientes.setMinimumSize(new java.awt.Dimension(145, 45));
        btClientes.setPreferredSize(new java.awt.Dimension(145, 45));
        btClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClientesActionPerformed(evt);
            }
        });
        pnMenu.add(btClientes);

        getContentPane().add(pnMenu, java.awt.BorderLayout.WEST);

        pnCentral.setBackground(new java.awt.Color(102, 255, 51));
        pnCentral.setLayout(new java.awt.CardLayout());

        pnCadastro.setBackground(new java.awt.Color(244, 247, 249));

        lbNome.setFont(new java.awt.Font("Corbel Light", 0, 18)); // NOI18N
        lbNome.setText("Nome:");

        lbiCPF.setFont(new java.awt.Font("Corbel Light", 0, 18)); // NOI18N
        lbiCPF.setText("CPF:");

        lbSobrenome.setFont(new java.awt.Font("Corbel Light", 0, 18)); // NOI18N
        lbSobrenome.setText("Sobrenome:");

        tfNome.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tfNome.setName("Nome"); // NOI18N

        tfSobrenome.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tfSobrenome.setName("Sobrenome"); // NOI18N

        lbDtnasc.setFont(new java.awt.Font("Corbel Light", 0, 18)); // NOI18N
        lbDtnasc.setText("Data de Nascimento:");

        btAdd.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        btAdd.setText("Adicionar");
        btAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddActionPerformed(evt);
            }
        });

        tfDtnasc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfDtnasc.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tfDtnasc.setPreferredSize(new java.awt.Dimension(6, 30));

        tfCPF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfCPF.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        tfCPF.setMinimumSize(new java.awt.Dimension(6, 30));
        tfCPF.setPreferredSize(new java.awt.Dimension(6, 30));

        spVagas.setModel(new javax.swing.SpinnerNumberModel(1, 1, 5, 1));
        spVagas.setValue(1);

        lbEscolheVaga.setText("Escolha a vaga que deseja cadastrar um novo candidato:");

        javax.swing.GroupLayout pnCadastroLayout = new javax.swing.GroupLayout(pnCadastro);
        pnCadastro.setLayout(pnCadastroLayout);
        pnCadastroLayout.setHorizontalGroup(
            pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCadastroLayout.createSequentialGroup()
                .addGap(252, 252, 252)
                .addComponent(btAdd)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnCadastroLayout.createSequentialGroup()
                .addGroup(pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnCadastroLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbEscolheVaga)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spVagas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnCadastroLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnCadastroLayout.createSequentialGroup()
                                .addComponent(lbDtnasc)
                                .addGap(37, 37, 37)
                                .addComponent(tfDtnasc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnCadastroLayout.createSequentialGroup()
                                .addGroup(pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lbSobrenome)
                                        .addComponent(lbNome))
                                    .addGroup(pnCadastroLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(lbiCPF)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnCadastroLayout.createSequentialGroup()
                                        .addComponent(tfCPF, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                        .addGap(192, 192, 192))
                                    .addComponent(tfSobrenome)
                                    .addComponent(tfNome))))))
                .addGap(77, 77, 77))
        );
        pnCadastroLayout.setVerticalGroup(
            pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spVagas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbEscolheVaga))
                .addGap(26, 26, 26)
                .addGroup(pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNome)
                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbSobrenome)
                    .addComponent(tfSobrenome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbiCPF)
                    .addComponent(tfCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(pnCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDtnasc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfDtnasc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addComponent(btAdd)
                .addGap(133, 133, 133))
        );

        pnCentral.add(pnCadastro, "Cadastro");

        tabela.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabela);

        btSalvar.setFont(new java.awt.Font("Corbel", 0, 18)); // NOI18N
        btSalvar.setText("Salvar");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnCandidatosLayout = new javax.swing.GroupLayout(pnCandidatos);
        pnCandidatos.setLayout(pnCandidatosLayout);
        pnCandidatosLayout.setHorizontalGroup(
            pnCandidatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnCandidatosLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(pnCandidatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnCandidatosLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnCandidatosLayout.createSequentialGroup()
                        .addComponent(btSalvar)
                        .addGap(247, 247, 247))))
        );
        pnCandidatosLayout.setVerticalGroup(
            pnCandidatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCandidatosLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btSalvar)
                .addGap(31, 31, 31))
        );

        pnCentral.add(pnCandidatos, "Candidatos");

        getContentPane().add(pnCentral, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadastroActionPerformed
        mostraPainel("Cadastro");
        lbTitulo.setText("Cadastrar novo candidato");
        
    }//GEN-LAST:event_btCadastroActionPerformed

    private void btClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClientesActionPerformed
        lbTitulo.setText("Candidatos");
        mostraPainel("Candidatos");
    }//GEN-LAST:event_btClientesActionPerformed

    private void btAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddActionPerformed
        if (escolheVaga() == true){
          Addlista();}
    }//GEN-LAST:event_btAddActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
       if (tabelaCont > 0){
       JOptionPane.showMessageDialog(null,"Todos os candidatos foram cadastrados com sucesso!","Atenção!",JOptionPane.INFORMATION_MESSAGE);}
       else {JOptionPane.showMessageDialog(null,"Por favor insira algum candidato!","Atenção!",JOptionPane.INFORMATION_MESSAGE);}
    }//GEN-LAST:event_btSalvarActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdd;
    private javax.swing.JButton btCadastro;
    private javax.swing.JButton btClientes;
    private javax.swing.JButton btSalvar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDtnasc;
    private javax.swing.JLabel lbEscolheVaga;
    private javax.swing.JLabel lbEspaco0;
    private javax.swing.JLabel lbEspaco1;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbSobrenome;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lbiCPF;
    private javax.swing.JPanel pnCadastro;
    private javax.swing.JPanel pnCandidatos;
    private javax.swing.JPanel pnCentral;
    private javax.swing.JPanel pnMenu;
    private javax.swing.JPanel pnTitulo;
    private javax.swing.JSpinner spVagas;
    private javax.swing.JTable tabela;
    private javax.swing.JFormattedTextField tfCPF;
    private javax.swing.JFormattedTextField tfDtnasc;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfSobrenome;
    // End of variables declaration//GEN-END:variables
}
