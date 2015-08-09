/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.felconix.modelo.strokes;

import java.awt.BasicStroke;
import java.awt.Shape;
import java.awt.Stroke;

/**
 *
 * @author Jorge
 */
public class CompositeStroke implements Stroke {
	private Stroke stroke1, stroke2;

	public CompositeStroke( Stroke stroke1, Stroke stroke2 ) {
		this.stroke1 = stroke1;
		this.stroke2 = stroke2;
	}
        
        public CompositeStroke()
        {
            this(new BasicStroke( 10f ), new BasicStroke( 0.5f ) );
        }

        @Override
	public Shape createStrokedShape( Shape shape ) {
		return stroke2.createStrokedShape( stroke1.createStrokedShape( shape ) );
	}
}
