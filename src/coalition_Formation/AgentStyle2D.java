package coalition_Formation;

import java.awt.Color;

import repast.simphony.visualizationOGL2D.DefaultStyleOGL2D;

/**
 * The 2D style for Schelling Model Agents.  
 *
 * @author Eric Tatara
 */

public class AgentStyle2D extends DefaultStyleOGL2D {

	

	@Override
	public Color getColor(Object o) {
			return Color.GREEN;
		
	}
}