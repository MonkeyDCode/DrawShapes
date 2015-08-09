/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.felconix.modelo;

import java.awt.Shape;
import java.util.ArrayList;


/**
 *
 * @author Vaio
 */
public class FiguraSalvar {
    
    private final String nombre;
    private final ArrayList<PuntosEditable> figura;

    public FiguraSalvar(String nombre, ArrayList<PuntosEditable> figura) {
        this.nombre = nombre;
        this.figura = figura;
    }

    public ArrayList<PuntosEditable> getFigura() {
        return figura;
    }

    public String getNombre() {
        return nombre;
    }
    
    
    
    
    
    
    
}
