package entities;

import java.sql.SQLException;
import java.util.List;

public interface CrudLivraison  <Liv> {
    public void ajouter(Liv l);
    public void modifier(Liv l);
    public void supprimer(int id) throws SQLException;
    public List<Livraison> Show();
}
