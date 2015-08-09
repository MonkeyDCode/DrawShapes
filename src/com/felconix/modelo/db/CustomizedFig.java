/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.felconix.modelo.db;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseReadOnlyException;
import com.db4o.ext.Db4oIOException;
import com.felconix.modelo.FiguraSalvar;
import com.felconix.modelo.PuntosEditable;
import java.awt.Shape;
import java.util.ArrayList;

/**
 *
 * @author Vaio
 */
public class CustomizedFig {
    
    private final ObjectContainer baseDatos;

    public CustomizedFig() {
        baseDatos = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),"Customized_Fig.db4o");
    }
    
    public ArrayList<String> nombresCustomizedFig()
    {
        ArrayList<String> aux = new ArrayList<>();
        FiguraSalvar fig = new FiguraSalvar(null,null);
        ObjectSet<FiguraSalvar> queryByExample = baseDatos.queryByExample(fig);
        for (FiguraSalvar figuraSalvar : queryByExample) {
            aux.add(figuraSalvar.getNombre());
        }
        return aux;
    }
    
    public void close()
    {
        baseDatos.close();
    }
    
    
    public ArrayList<PuntosEditable> getPersonalizedFig(String nombre)
    {
        FiguraSalvar fig = new FiguraSalvar(nombre, null);
        ObjectSet<FiguraSalvar> queryByExample = baseDatos.queryByExample(fig);
        return queryByExample.next().getFigura();
    }
    
    public boolean salvarFigura(FiguraSalvar fig)
    {
        boolean flag = true;
        try 
        {
            FiguraSalvar fug;
            if(baseDatos.queryByExample(new FiguraSalvar(fig.getNombre(), null)).hasNext())
            {
                fug = (FiguraSalvar)baseDatos.queryByExample(new FiguraSalvar(fig.getNombre(), null)).next();
                baseDatos.delete(fug);
                flag = false;
            }
            baseDatos.store(fig);
            baseDatos.commit();
            return flag;
        } 
        catch (Db4oIOException | DatabaseClosedException | DatabaseReadOnlyException e) 
        {
            return false;
        }
        
    }
    
    
}
