import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntradaPseudocodigo extends JDialog {
    private JTextArea textArea;
    private JButton   botonAceptar;
    private String    pseudocodigo;

    public EntradaPseudocodigo(Frame parent, String titulo, String textoInicial)
    {
        super(parent, titulo, true);

        this.pseudocodigo = textoInicial;

        initComponents(textoInicial);
    }

    private void initComponents(String textoInicial)
    {

        setLayout(new BorderLayout());


        textArea = new JTextArea(10, 30);
        textArea.setText(textoInicial);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);


        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);


        botonAceptar = new JButton("Aceptar");
        botonAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                pseudocodigo = textArea.getText();

                dispose();
            }
        });
        add(botonAceptar, BorderLayout.SOUTH);


        pack();
        setLocationRelativeTo(getParent());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public String getPseudocodigo()
    {
       return this.pseudocodigo;
    }
}