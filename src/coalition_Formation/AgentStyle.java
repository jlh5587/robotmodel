package coalition_Formation;

import java.awt.Color;
import java.awt.Font;

import java.awt.Color;
import java.awt.Font;

import javax.media.j3d.Shape3D;

import repast.simphony.visualization.visualization3D.AppearanceFactory;
import repast.simphony.visualization.visualization3D.ShapeFactory;
import repast.simphony.visualization.visualization3D.style.Style3D;
import repast.simphony.visualization.visualization3D.style.TaggedAppearance;
import repast.simphony.visualization.visualization3D.style.TaggedBranchGroup;

public class AgentStyle implements Style3D<Agent> {

	public TaggedBranchGroup getBranchGroup(Agent agent, TaggedBranchGroup taggedGroup) {

		if (taggedGroup == null || taggedGroup.getTag() == null) {
			taggedGroup = new TaggedBranchGroup("DEFAULT");
			Shape3D sphere = ShapeFactory.createSphere(.03f, "DEFAULT");
			sphere.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
			taggedGroup.getBranchGroup().addChild(sphere);

			return taggedGroup;
		}
		return null;
	}

	public float[] getRotation(Agent agent) {
		return null;
	}

	public String getLabel(Agent agent, String currentLabel) {
		return null; 
	}

	public Color getLabelColor(Agent agent, Color currentColor) {
		return Color.YELLOW;
	}

	public Font getLabelFont(Agent agent, Font currentFont) {
		return null;
	}

	public LabelPosition getLabelPosition(Agent agent, LabelPosition curentPosition) {
		return LabelPosition.NORTH;
	}

	public float getLabelOffset(Agent agent) {
		return .035f;
	}

	public TaggedAppearance getAppearance(Agent agent, TaggedAppearance taggedAppearance, Object shapeID) {
		if (taggedAppearance == null) {
			taggedAppearance = new TaggedAppearance();
		}
		  AppearanceFactory.setMaterialAppearance(taggedAppearance.getAppearance(), Color.green);
		
		
		return taggedAppearance;
	}

	public float[] getScale(Agent agent) {
		return null;
	}
}