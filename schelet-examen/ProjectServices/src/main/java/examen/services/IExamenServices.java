package examen.services;

import examen.model.Player;

public interface IExamenServices {

    void login(Player player, IExamenObserver client) throws ExamenException;
    void logout(Player player, IExamenObserver client) throws ExamenException;
    public void update() throws ExamenException;

}
