/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.felconix.modelo;

import java.util.ArrayList;

/**
 *
 * @author Vaio
 */
public class PanelSave2 {
    
    
    private final String nombre;
    private final ArrayList<PanelSave> figuras;

    public PanelSave2(ArrayList<PanelSave> figuras, String nombre) {
        this.figuras = figuras;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    
    

    public ArrayList<PanelSave> getFiguras() {
        return figuras;
    }
    
    
    
}
