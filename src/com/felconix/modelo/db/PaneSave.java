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
import com.felconix.modelo.PanelSave;
import com.felconix.modelo.PanelSave2;
import java.util.ArrayList;

/**
 *
 * @author Vaio
 */
public class PaneSave {
    
    private ObjectContainer baseDatos;

    public PaneSave() {
        baseDatos = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),"Pane_save.db4o");
    }
    
    public ArrayList<String> nombresPaneles()
    {
        ArrayList<String> aux = new ArrayList<>();
        PanelSave2 fig = new PanelSave2(null,null);
        ObjectSet<PanelSave2> queryByExample = baseDatos.queryByExample(fig);
        for (PanelSave2 figuraSalvar : queryByExample) {
            aux.add(figuraSalvar.getNombre());
        }
        return aux;
    }
    
    public void close()
    {
        baseDatos.close();
    }
    
    
    public ArrayList<PanelSave> getFiguras(String nombre)
    {
        PanelSave2 fig = new PanelSave2(null, nombre);
        ObjectSet<PanelSave2> queryByExample = baseDatos.queryByExample(fig);
        return queryByExample.next().getFiguras();
    }
    
    public boolean salvarFigura(PanelSave2 fig)
    {
        boolean flag = true;
        try 
        {
            PanelSave2 fug;
            if(baseDatos.queryByExample(new PanelSave2(null,fig.getNombre())).hasNext())
            {
                fug = (PanelSave2)baseDatos.queryByExample(new PanelSave2(null,fig.getNombre())).next();
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
