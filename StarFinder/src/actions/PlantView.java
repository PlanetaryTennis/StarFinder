package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import planetary.Plant;

public class PlantView implements ActionListener {

	private Plant myPlant;
	
	public PlantView(Plant plant) {
		myPlant = plant;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame look = new JFrame("Plant View");
		String display = "";
		display += myPlant.isDoesFruit() ? "Bares Fruit\n":"Does not Bares Fruit\n";
		display += myPlant.isHasMaterial() ? "Has useable material\n":"Does not have usable material\n";
		display += myPlant.isCarnivorous() ? "Carnivorous\n":"Non-Carnivorous\n";
		display += myPlant.isCanCamo() ? "Can Blend\n":"Can not Blend\n";
		display += myPlant.isVenomous() ? "Is Venomous, Venom Scale: "+myPlant.getVenomCatagory()+"\n":"Is not Venomous\n";
		display += myPlant.isPosionous() ? "Is Posionous, Posion Scale: "+myPlant.getPosionCatagory()+"\n":"Is not Posionous\n";
		
		display += "The Plant is in Size Catagory " + myPlant.getSizeCatagory() + "\n";
		display += "The Plant bares " + myPlant.getBirthRate() + " each litter\n";
		display += "The Plant matrues in " + (double)(myPlant.getAgeRate()) + " local 12th of years\n";
		display += "The Plant lives for " + myPlant.getOldAge() + " local years\n";
		JTextArea Read = new JTextArea(display);
		Read.setEditable(false);
		look.add(Read);
		look.setSize(400, 400);
		look.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		look.setVisible(true);
	}
}
