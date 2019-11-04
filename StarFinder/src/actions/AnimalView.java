package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import planetary.Animal;

public class AnimalView implements ActionListener {

	Animal myAnimal;

	public AnimalView(Animal animal) {
		myAnimal = animal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame look = new JFrame("Animal View");
		String display = "";
		display += myAnimal.isCanWalk() ? "Walks\n" : "Does not Walk\n";
		display += myAnimal.isCanSwim() ? "Swims\n" : "Does not Swims\n";
		display += myAnimal.isCanFly() ? "Flies\n" : "Does not Fly\n";
		display += myAnimal.isCanBurrow() ? "Burrow\n" : "Does not Burrow\n";
		display += myAnimal.isCanSee() ? "Can See\n" : "Can not See\n";
		display += myAnimal.isCanHear() ? "Can Hear\n" : "Can not Hear\n";
		display += myAnimal.isCanSmell() ? "Can Smell\n" : "Can not Smell\n";
		display += myAnimal.isCanWork() ? "Can Labor\n" : "Can not Labor\n";
		display += myAnimal.isLaysEggs() ? "Lays Eggs\n" : "Does not Lays Eggs\n";
		display += myAnimal.isHasMilk() ? "Produces Milk\n" : "Does not Produce Milk\n";
		display += myAnimal.isThickHide() ? "Has Thick Hide\n" : "Does not Have Thick Hide\n";
		display += myAnimal.isCanCamo() ? "Can Blend\n" : "Can not Blend\n";
		display += myAnimal.isVenomous() ? "Is Venomous, Venom Scale: " + myAnimal.getVenomCatagory() + "\n"
				: "Is not Venomous\n";
		display += myAnimal.isPosionous() ? "Is Posionous, Posion Scale: " + myAnimal.getPosionCatagory() + "\n"
				: "Is not Posionous\n";

		boolean meat = myAnimal.isEatsMeat();
		boolean plant = myAnimal.isEatsPlants();
		boolean metal = myAnimal.isEatsMetal();

		if (meat && plant && metal) {
			display += "Is Omnivorous Lithovore\n";
		} else if (meat && plant) {
			display += "Is Omnivorous\n";
		} else if (meat && metal) {
			display += "Is Carnivorous Lithovore\n";
		} else if (plant && metal) {
			display += "Is Herbivorous Lithovore\n";
		} else if (meat) {
			display += "Is Carnivorous\n";
		} else if (plant) {
			display += "Is Herbivorous\n";
		}

		display += "The Animal is in Size Catagory " + myAnimal.getSizeCatagory() + "\n";
		display += "The Animal has " + myAnimal.getLegPairs() * 2 + " legs\n";
		display += "The Animal has " + myAnimal.getWingPairs() * 2 + " wings\n";
		display += "The Animal has " + myAnimal.getHeavyManipulatorPairs() * 2 + " heavy manipulators\n";
		display += "The Animal has " + myAnimal.getManipulatorPairs() * 2 + " manipulators\n";
		display += "The Animal has " + myAnimal.getFineManipulatorPairs() * 2 + " fine manipulators\n";
		display += "The Animal has " + myAnimal.getEyes() + " eyes\n";
		display += "The Animal bares " + myAnimal.getBirthRate() + " each litter\n";
		display += "The Animal matrues in " + (double) (myAnimal.getAgeRate()) + " local 12th of years\n";
		display += "The Animal lives for " + myAnimal.getOldAge() + " local years\n";
		JTextArea Read = new JTextArea(display);
		Read.setEditable(false);
		look.add(Read);
		look.setSize(400, 600);
		look.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		look.setVisible(true);
	}

}
