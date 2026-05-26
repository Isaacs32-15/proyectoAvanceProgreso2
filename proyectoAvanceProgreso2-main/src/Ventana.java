import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

public class Ventana {
    private JPanel Principal;
    private JTabbedPane tabbedPane1;


    private JTextField txtCodigo;
    private JTextField txtTitulo;
    private JTextField txtUsuario;
    private JTextField txtxtDescripcion;
    private JButton btnGuardar;
    private JTextField txtBuscarCodigo;
    private JButton btnBuscar;
    private JButton btnVaciar;
    private JTextArea txtMostrar;
    private JButton btnMostrar;
    private JButton btnEditar;
    private JComboBox<String> cmbEstado;
    private JButton btnEliminar;

    private JPanel AtencionSoporte;
    private JComboBox cmbTecnico;
    private JComboBox cmbEstadoSoporte;
    private JTextArea txtRespuesta;
    private JTable tablaSoporte;
    private JButton btnAsignar;
    private JButton btnResolver;
    private JButton btnLimpiarSoporte;
    private JList listaTickets;

    private GestiondeTickets gestion = new GestiondeTickets();


    private void limpiarCampos() {
        txtCodigo.setText("");
        txtTitulo.setText("");
        txtUsuario.setText("");
        txtxtDescripcion.setText("");
    }


    public Ventana() {


        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    int codigo =
                            Integer.parseInt(txtCodigo.getText());

                    String titulo =
                            txtTitulo.getText();

                    String nombreUsuario =
                            txtUsuario.getText();

                    String descripcion =
                            txtxtDescripcion.getText();

                    if (titulo.isEmpty() ||
                            nombreUsuario.isEmpty() ||
                            descripcion.isEmpty()) {

                        JOptionPane.showMessageDialog(
                                null,
                                "Debe llenar todos los campos"
                        );

                        return;
                    }

                    boolean ticketExiste = false;
                    boolean usuarioExiste = false;

                    for (Ticket ticket :
                            gestion.getTickets()) {

                        if (ticket.getCodigo() == codigo) {
                            ticketExiste = true;
                        }

                        if (ticket.getUsuario() != null &&
                                ticket.getUsuario()
                                        .getNombre()
                                        .equalsIgnoreCase(nombreUsuario)) {

                            usuarioExiste = true;
                        }
                    }

                    if (ticketExiste) {

                        JOptionPane.showMessageDialog(
                                null,
                                "Ya existe un ticket con ese código"
                        );

                        return;
                    }

                    if (usuarioExiste) {

                        JOptionPane.showMessageDialog(
                                null,
                                "Ya existe un usuario con ese nombre"
                        );

                        return;
                    }

                    Usuario usuario =
                            new Usuario(
                                    codigo,
                                    nombreUsuario,
                                    titulo
                            );

                    Ticket ticket =
                            new Ticket(
                                    codigo,
                                    titulo,
                                    descripcion,
                                    "Abierto",
                                    usuario
                            );

                    gestion.getTickets().add(ticket);

                    JOptionPane.showMessageDialog(
                            null,
                            "Ticket guardado correctamente"
                    );

                    limpiarCampos();

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                            null,
                            "El código debe ser un número"
                    );
                }
            }
        });


        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    int codigo =
                            Integer.parseInt(
                                    txtBuscarCodigo.getText()
                            );

                    Ticket ticket =
                            gestion.buscarTicketPorCodigo(codigo);

                    if (ticket != null) {

                        String nuevaDescripcion =
                                txtxtDescripcion.getText();

                        String nuevoEstado =
                                cmbEstado
                                        .getSelectedItem()
                                        .toString();

                        ticket.setDescripcion(
                                nuevaDescripcion
                        );

                        ticket.setEstado(
                                nuevoEstado
                        );

                        JOptionPane.showMessageDialog(
                                null,
                                "Ticket editado correctamente"
                        );

                    } else {

                        JOptionPane.showMessageDialog(
                                null,
                                "No se encontró un ticket"
                        );
                    }

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Código inválido"
                    );
                }
            }
        });


        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    int codigo =
                            Integer.parseInt(
                                    txtBuscarCodigo.getText()
                            );

                    Ticket ticket =
                            gestion.buscarTicketPorCodigo(codigo);

                    if (ticket != null) {

                        txtMostrar.setText(
                                ticket.toString()
                        );

                        JOptionPane.showMessageDialog(
                                null,
                                "Ticket encontrado"
                        );

                    } else {

                        JOptionPane.showMessageDialog(
                                null,
                                "No se encontró el ticket"
                        );
                    }

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Código inválido"
                    );
                }
            }
        });


        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                txtMostrar.setText(
                        gestion.mostrarTicketsAscendenteBurbuja()
                );
            }
        });


        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    int codigo =
                            Integer.parseInt(
                                    txtBuscarCodigo.getText()
                            );

                    Ticket ticket =
                            gestion.buscarTicketPorCodigo(codigo);

                    if (ticket != null) {

                        gestion.eliminarTicketPorCodigo(codigo);

                        JOptionPane.showMessageDialog(
                                null,
                                "Ticket eliminado"
                        );

                    } else {

                        JOptionPane.showMessageDialog(
                                null,
                                "No existe ese ticket"
                        );
                    }

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Código inválido"
                    );
                }
            }
        });


        btnVaciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                gestion.vaciarTickets();

                txtMostrar.setText("");

                JOptionPane.showMessageDialog(
                        null,
                        "Todos los tickets eliminados"
                );
            }
        });

        DefaultTableModel modeloTabla =
                new DefaultTableModel();

        modeloTabla.addColumn("Ticket");
        modeloTabla.addColumn("Técnico");
        modeloTabla.addColumn("Estado");
        modeloTabla.addColumn("Respuesta");

        tablaSoporte.setModel(modeloTabla);


        btnAsignar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String ticket =
                        listaTickets.getSelectedValue().toString();

                String tecnico =
                        cmbTecnico
                                .getSelectedItem()
                                .toString();

                String estado =
                        cmbEstadoSoporte
                                .getSelectedItem()
                                .toString();

                String respuesta =
                        txtRespuesta.getText();

                modeloTabla.addRow(new Object[]{

                        ticket,
                        tecnico,
                        estado,
                        respuesta
                });

                JOptionPane.showMessageDialog(
                        null,
                        "Ticket asignado correctamente"
                );
            }
        });


        btnResolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int fila =
                        tablaSoporte.getSelectedRow();

                if (fila >= 0) {

                    tablaSoporte.setValueAt(
                            "Resuelto",
                            fila,
                            2
                    );

                    JOptionPane.showMessageDialog(
                            null,
                            "Ticket resuelto"
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            null,
                            "Seleccione una fila"
                    );
                }
            }
        });

        btnLimpiarSoporte.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ) {

                        txtRespuesta.setText("");

                        listaTickets.clearSelection();

                        cmbTecnico.setSelectedIndex(0);

                        cmbEstadoSoporte.setSelectedIndex(0);

                        tablaSoporte.clearSelection();

                        JOptionPane.showMessageDialog(
                                null,
                                "Campos limpiados"
                        );
                    }
                }
        );
    }


    public static void main(String[] args) {

        JFrame frame =
                new JFrame("Ventana");

        frame.setContentPane(
                new Ventana().Principal
        );

        frame.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        frame.pack();

        frame.setVisible(true);
    }
}