import java.util.ArrayList;

public class GestiondeTickets {

    private ArrayList<Ticket> tickets;

    public GestiondeTickets() {
        tickets = new ArrayList<>();
    }

    public boolean agregarTicket(Ticket ticket) {
        if (existeTicket(ticket.getCodigo())) {
            return false;
        }

        tickets.add(ticket);
        return true;
    }

    public boolean existeTicket(int codigo) {
        for (Ticket ticket : tickets) {
            if (ticket.getCodigo() == codigo) {
                return true;
            }
        }

        return false;
    }

    public boolean existeUsuario(String nombreUsuario) {
        for (Ticket ticket : tickets) {
            if (ticket.getUsuario() != null &&
                    ticket.getUsuario().getNombre().equalsIgnoreCase(nombreUsuario)) {
                return true;
            }
        }

        return false;
    }

    public Usuario buscarUsuarioPorNombre(String nombreUsuario) {
        for (Ticket ticket : tickets) {
            if (ticket.getUsuario() != null &&
                    ticket.getUsuario().getNombre().equalsIgnoreCase(nombreUsuario)) {
                return ticket.getUsuario();
            }
        }

        return null;
    }


    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void mostrarTickets() {
        for (Ticket ticket : tickets) {
            System.out.println(ticket);
        }
    }

    public Ticket buscarTicketPorCodigo(int codigo) {
        return buscarTicketPorCodigoRecursivo(codigo, 0);
    }

    private Ticket buscarTicketPorCodigoRecursivo(int codigo, int indice) {
        if (indice >= tickets.size()) {
            return null;
        }

        if (tickets.get(indice).getCodigo() == codigo) {
            return tickets.get(indice);
        }

        return buscarTicketPorCodigoRecursivo(codigo, indice + 1);
    }

    public void vaciarTickets() {
        tickets.clear();
    }

    public boolean eliminarTicketPorCodigo(int codigo) {
        Ticket ticket = buscarTicketPorCodigo(codigo);

        if (ticket != null) {
            tickets.remove(ticket);
            return true;
        }

        return false;
    }

    public String mostrarTicketsAscendenteBurbuja() {
        if (tickets.isEmpty()) {
            return "No hay tickets registrados.";
        }

        ArrayList<Ticket> ticketsOrdenados = new ArrayList<>(tickets);

        for (int i = 0; i < ticketsOrdenados.size() - 1; i++) {
            for (int j = 0; j < ticketsOrdenados.size() - 1 - i; j++) {
                if (ticketsOrdenados.get(j).getCodigo() > ticketsOrdenados.get(j + 1).getCodigo()) {
                    Ticket aux = ticketsOrdenados.get(j);
                    ticketsOrdenados.set(j, ticketsOrdenados.get(j + 1));
                    ticketsOrdenados.set(j + 1, aux);
                }
            }
        }

        StringBuilder resultado = new StringBuilder();

        for (Ticket ticket : ticketsOrdenados) {
            resultado.append(ticket).append("\n");
        }

        return resultado.toString();
    }
}