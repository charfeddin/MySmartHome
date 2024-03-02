package entities;

import java.util.List;
import java.sql.SQLException;


public interface CrudCommande <Cmd> {
    public void ajouter(Cmd c);
    public void modifier(Cmd c);
    public void supprimer(int id) throws SQLException;
    public List<Commande> Show();

}
