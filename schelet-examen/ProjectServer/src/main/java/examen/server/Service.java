package examen.server;

import examen.model.Player;
import examen.persistence.repository.IPlayerRepo;
import examen.services.ExamenException;
import examen.services.IExamenObserver;
import examen.services.IExamenServices;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Service implements IExamenServices {

    private IPlayerRepo repoPlayer;


    private Map<String, IExamenObserver> loggedPlayers;

    private final int defaultThreadsNo = 5;


    public Player findByUsername(String username) {
        return repoPlayer.findByUsername(username);
    }

    public Service(IPlayerRepo repoPlayer) {
        this.repoPlayer = repoPlayer;
        this.loggedPlayers = new ConcurrentHashMap<>();
    }


    @Override
    public synchronized void login(Player player, IExamenObserver client) throws ExamenException {
        Player v = repoPlayer.findByUsername(player.getUsername());
        System.out.println(v);
        if (v != null) {
            System.out.println("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            if (!v.getPassword().equals(player.getPassword()))
                throw new ExamenException("Username sau parola incorecte");
            System.out.println("Bbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
            if (loggedPlayers.get(v.getUsername()) != null)
                throw new ExamenException("Voluntarul este deja logat.");
            loggedPlayers.put(player.getUsername(), client);
            System.out.println("ccccccccccccccccccccccccccccccccccc");
        } else {
            throw new ExamenException("Autentificare esuata");
        }
    }

    @Override
    public void logout(Player player, IExamenObserver client) throws ExamenException {
        IExamenObserver localClient = loggedPlayers.remove(player.getUsername());
        if (localClient==null)
            throw new ExamenException("User "+player.getUsername()+" is not logged in.");
    }

    @Override
    public void update() throws ExamenException {

    }
}
