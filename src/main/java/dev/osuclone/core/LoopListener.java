package dev.osuclone.core;


public interface LoopListener {

    /** Met à jour l'état du jeu. Temps écoulé depuis la dernière image */
    void update(double deltaSeconds);

    // Dessine l'état actuel du jeu. 
    void render();
}