public interface ProduttoreMsgInterface {
    void send(Msg m) throws DestinatarioPieno;

    class DestinatarioPieno extends Exception {
        int dest;

        public DestinatarioPieno(int dest) {
            this.dest = dest;
        }

        @Override
        public String getLocalizedMessage() {
            return "Recipient " + dest + " is full!!";
        }
    }
}
