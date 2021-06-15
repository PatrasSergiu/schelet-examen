package examen.persistence.repository;

import examen.model.Player;

public interface IPlayerRepo extends IRepository<Long, Player> {

    public abstract Player findByUsername(String username);

}