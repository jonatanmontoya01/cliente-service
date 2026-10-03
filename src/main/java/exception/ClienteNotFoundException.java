package clienteservicie.domain.exception;

public class ClienteNotFoundException extends RuntimeException {

    private final Long id;

    public ClienteNotFoundException(Long id) {
        super("El cliente con ID " + id + " no existe en la base de datos.");
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}