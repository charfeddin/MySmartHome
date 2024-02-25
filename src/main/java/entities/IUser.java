package entities;

import java.util.List;

public interface IUser <T>{
    public void ajouter(T p);
    public void supprimer(int id);
    public void modifier(T p);
    public List<T> getAll();
    public T getOneById(int id);
}
