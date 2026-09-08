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

public class TelaReserva extends JFrame {
   public TelaReserva(int var1) {
      this.setTitle("Realizar Reserva");
      this.setSize(400, 300);
      this.setDefaultCloseOperation(3);
      this.setLocationRelativeTo((Component)null);
      List var2 = manipuladorArquivos.lerLeitores();
      List var3 = manipuladorArquivos.lerLivros();
      JComboBox var4 = new JComboBox();

      for(Leitor var6 : var2) {
         var4.addItem(var6);
      }

      JComboBox var11 = new JComboBox();

      for(Livro var7 : var3) {
         var11.addItem(var7);
      }

      JTextField var13 = new JTextField();
      JTextField var14 = new JTextField();
      JPanel var8 = new JPanel(new GridLayout(5, 2, 10, 10));
      var8.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
      var8.add(new JLabel("Leitor:"));
      var8.add(var4);
      var8.add(new JLabel("Livro:"));
      var8.add(var11);
      var8.add(new JLabel("Data da retirada (dd/MM/yyyy):"));
      var8.add(var13);
      JButton var9 = new JButton("Reservar");
      var9.addActionListener((var6x) -> {
         int var7 = Integer.parseInt(var4.getSelectedItem().toString().split(" - ")[0]);
         int var8 = Integer.parseInt(var11.getSelectedItem().toString().split(" - ")[0]);
         String var9 = var13.getText().trim();
         String var10 = var14.getText().trim();
         ReservaControle.reservarLivro(var7, var8, var9, var10, this, var1);
      });
      JButton var10 = new JButton("Voltar");
      var10.addActionListener((var2x) -> {
         this.dispose();
         new MenuReserva(var1);
      });
      var8.add(var10);
      var8.add(var9);
      this.add(var8);
      this.setVisible(true);
   }
}
