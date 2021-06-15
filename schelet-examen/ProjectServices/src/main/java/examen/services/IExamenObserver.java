package examen.services;

import java.rmi.RemoteException;

public interface IExamenObserver {

    void update() throws ExamenException, RemoteException;

}
