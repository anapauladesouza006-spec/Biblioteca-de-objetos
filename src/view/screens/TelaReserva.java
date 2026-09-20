// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package view.screens;

import controle.ReservaControle;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import modelo.Reserva;
import modelo.Leitor;
import modelo.Livro;
import util.manipuladorArquivos;
import view.menus.MenuReserva;
import view.menus.MenuSecretaria;

public class TelaReserva extends JFrame {
   public TelaReserva(int idSecretaria) {
      this.setTitle("Realizar Reserva");
      this.setSize(400, 300);
      this.setDefaultCloseOperation(3);
      this.setLocationRelativeTo((Component)null);

      List<Leitor> leitores = manipuladorArquivos.lerLeitores();
      List<Livro> livros = manipuladorArquivos.lerLivros();
      JComboBox<Leitor> cmbLeitor = new JComboBox<>();

      for(Leitor l : leitores) {
         cmbLeitor.addItem(l);
      }

      JComboBox<Livro> cmbLivro = new JComboBox<>();
      for(Livro l : livros){
         cmbLivro.addItem(l);
      }

      JTextField txtRetirada = new JTextField();
      
      JPanel painel = new JPanel(new GridLayout(5, 2, 10, 10));
      
      painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
      painel.add(new JLabel("Leitor:"));
      painel.add(cmbLeitor);
      painel.add(new JLabel("Livro:"));
      painel.add(cmbLivro);
      painel.add(new JLabel("Data da retirada (dd/MM/yyyy):"));
      painel.add(txtRetirada);
      JButton btnReservar = new JButton("Reservar");
      btnReservar.addActionListener((var6x) -> {
         int id_leitor = Integer.parseInt(cmbLeitor.getSelectedItem().toString().split(" - ")[0]);
         int id_livro = Integer.parseInt(cmbLivro.getSelectedItem().toString().split(" - ")[0]);
         String data = txtRetirada.getText().trim();
         /*ReservaControle.reservarLivro(var7, painel, var9, var10, this, var1);*/
      });
      JButton btnVoltar = new JButton("Voltar");
      btnVoltar.addActionListener((var2x) -> {
         this.dispose();
         new MenuSecretaria(idSecretaria);
      });
      painel.add(btnVoltar);
      painel.add(btnReservar);
      this.add(painel);
      this.setVisible(true);
   }
}
