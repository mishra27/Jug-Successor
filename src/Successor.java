///////////////////////////////////////////////////////////////////////////////
//                 Title:            HW2
// Files:            Successor.java
// Semester:         Fall 2017
//
// Author:           Akshay Mishra, mishra27@wisc.edu
//
//////////////////////////// 80 columns wide //////////////////////////////////


import java.util.*;

public class Successor {
	public static class JugState {

		// arrays to store capacity and current content of jug
		int[] Capacity = new int[] { 0, 0, 0 };
		int[] Content = new int[] { 0, 0, 0 };

		/**
		 * This method helps in copying capacity and content of a different
		 * state 
		 * @param copyFrom JugState from which we will copy all content and 
		 * capacity
		 */
		public JugState(JugState copyFrom) {
			this.Capacity[0] = copyFrom.Capacity[0];
			this.Capacity[1] = copyFrom.Capacity[1];
			this.Capacity[2] = copyFrom.Capacity[2];
			this.Content[0] = copyFrom.Content[0];
			this.Content[1] = copyFrom.Content[1];
			this.Content[2] = copyFrom.Content[2];
		}

		public JugState() {
		}

		/**
		 * Method to initialize capacity of jugState
		 * @param A capacity of Jug 1
		 * @param B capacity of Jug 2
		 * @param C capacity of Jug 3
		 */
		public JugState(int A, int B, int C) {
			this.Capacity[0] = A;
			this.Capacity[1] = B;
			this.Capacity[2] = C;
		}


		/**
		 * Method to initialize contents and capacity of jugs
		 * @param A capacity of Jug 1
		 * @param B capacity of Jug 2
		 * @param C capacity of Jug 3
		 * @param a content of jug 1
		 * @param b content of jug 2
		 * @param c content of jug 3
		 */
		public JugState(int A, int B, int C, int a, int b, int c) {
			this.Capacity[0] = A;
			this.Capacity[1] = B;
			this.Capacity[2] = C;
			this.Content[0] = a;
			this.Content[1] = b;
			this.Content[2] = c;
		}

		/**
		 * Print method to print JugState
		 */
		public void printContent() {
			System.out.println(this.Content[0] + " " + this.Content[1] 
					+ " " + this.Content[2]);
		}

		/**
		 * This method will add all reachable jugState, in ome move, to an 
		 * array list. We will use this array list later to print out all the
		 * states
		 * @return an arrayList of all reachable jugStates in 1 move
		 */
		public ArrayList<JugState> getNextStates() {
			ArrayList<JugState> successors = new ArrayList<>();

			// This loop deals with cases of empty jugs
			// if they are not empty, it empties them
			// if not full, then fills them up
			for (int i = 0; i < 3; i++) 
			{

				// empty jugs if not empty
				if (this.Content[i] != 0) 
				{
					// creates new state, empties the current jug, and adds to
					// the list
					JugState temp = new JugState(this);
					temp.Content[i] = 0;					
					successors.add(temp);

				}

				// fill up jugs if not filled
				if (this.Content[i] != this.Capacity[i])
				{
					// creates new state, fills up the current jug, and adds to
					// the list
					JugState temp = new JugState(this);
					temp.Content[i] = temp.Capacity[i];					
					successors.add(temp);

				}


			}

			// below compare all possible pairs of the jug, and determines if
			// we can transfer water to current jug

			// keeps track of available space in jug for water
			int canTake;



			// if first jug is not full, then we can add more water
			if (this.Content[0] != this.Capacity[0])
			{
				JugState temp = new JugState(this);

				canTake = temp.Capacity[0]-temp.Content[0];

				//comparing with 2nd jug, if available space is more
				// than content of 2nd jug, then transfers all the water from
				// 2nd jug to 1st, and sets 2nd jug to 0
				if (canTake > temp.Content[1] && temp.Content[1] >0){
					temp.Content[0] = temp.Content[0] + temp.Content[1];
					temp.Content[1] = 0;
					successors.add(temp);
				}			

				// if not the transfers as much as it can
				else if (temp.Content[1] >0) {
					temp = new JugState(this);
					temp.Content[0] = temp.Content[0] + canTake;
					temp.Content[1] = temp.Content[1] - canTake;
					successors.add(temp);				
				}

				// comparing with 3rd jug, if available space is more
				// than content of 3rd jug, then transfers all the water from
				// 3rd jug to 1st, and sets 3rd jug to 0
				if (canTake > temp.Content[2] && temp.Content[2] >0){
					temp = new JugState(this);

					temp.Content[0] = temp.Content[0] + temp.Content[2];
					temp.Content[2] = 0;
					successors.add(temp);

				}			

				// if not the transfers as much as it can
				else if (temp.Content[2] >0){
					temp = new JugState(this);
					temp.Content[0] = temp.Content[0] + canTake;
					temp.Content[2] = temp.Content[2] - canTake;
					successors.add(temp);	

				}

			}
			
			// if second jug is not full, then we can add more water
			if (this.Content[1] != this.Capacity[1])
			{
				JugState temp = new JugState(this);

				// left space
				canTake = temp.Capacity[1]-temp.Content[1];
				
				//comparing with 3rd jug, if available space is more
				// than content of 3rd jug, then transfers all the water from
				// 3rd jug to 2nd, and sets 3rd jug to 0
				if (canTake > temp.Content[2] && temp.Content[2] >0){
					temp.Content[1] = temp.Content[1] + temp.Content[2];
					temp.Content[2] = 0;
					successors.add(temp);
				}			

				// if not the transfers as much as it can
				else if (temp.Content[2] >0){
					temp = new JugState(this);
					temp.Content[1] = temp.Content[1] + canTake;
					temp.Content[2] = temp.Content[2] - canTake;
					successors.add(temp);				
				}

				//comparing with 1st jug, if available space is more
				// than content of 1st jug, then transfers all the water from
				// 1st jug to 2nd, and sets 1st jug to 0
				if (canTake > temp.Content[0] && temp.Content[0] >0){
					temp = new JugState(this);

					temp.Content[1] = temp.Content[1] + temp.Content[0];
					temp.Content[0] = 0;
					successors.add(temp);
				}			

				// if not the transfers as much as it can
				else if(temp.Content[0] >0) {
					temp = new JugState(this);
					temp.Content[1] = temp.Content[1] + canTake;
					temp.Content[0] = temp.Content[0] - canTake;
					successors.add(temp);				
				}

			}

			
			// if third jug is not full, then we can add more water
			if (this.Content[2] != this.Capacity[2])
			{
				JugState temp = new JugState(this);

				canTake = temp.Capacity[2]-temp.Content[2];

				// comparing with 1st jug, if available space is more
				// than content of 1st jug, then transfers all the water from
				// 1st jug to 3rd, and sets 1st jug to 0
				if (canTake > temp.Content[0] && temp.Content[0] >0){
					temp.Content[2] = temp.Content[2] + temp.Content[0];
					temp.Content[0] = 0;
					successors.add(temp);
					//System.out.println("added1" + temp.Content[0]);

				}			

				// if not the transfers as much as it can
				else if (temp.Content[0] >0){
					temp = new JugState(this);
					temp.Content[2] = temp.Content[2] + canTake;
					temp.Content[0] = temp.Content[0] - canTake;
					successors.add(temp);	

				}

				// comparing with 2nd jug, if available space is more
				// than content of 2nd jug, then transfers all the water from
				// 2nd jug to 3rd, and sets 2nd jug to 0
				if (canTake > temp.Content[1] && temp.Content[1] >0){
					temp = new JugState(this);

					temp.Content[2] = temp.Content[2] + temp.Content[1];
					temp.Content[1] = 0;
					successors.add(temp);

				}			

				// if not the transfers as much as it can
				else if (temp.Content[1] >0){
					temp = new JugState(this);
					temp.Content[2] = temp.Content[2] + canTake;
					temp.Content[1] = temp.Content[1] - canTake;
					successors.add(temp);	

				}

			}

			return successors;
		}
	}

	public static void main(String[] args) {
		if (args.length != 6) {
			System.out.println("Usage: java successor [A] [B] [C] [a] [b] [c]");
			return;
		}

		// parse command line arguments
		JugState a = new JugState();
		a.Capacity[0] = Integer.parseInt(args[0]);
		a.Capacity[1] = Integer.parseInt(args[1]);
		a.Capacity[2] = Integer.parseInt(args[2]);
		a.Content[0] = Integer.parseInt(args[3]);
		a.Content[1] = Integer.parseInt(args[4]);
		a.Content[2] = Integer.parseInt(args[5]);

		// Implement this function
		ArrayList<JugState> asist = a.getNextStates();
		// a.printContent();
		// Print out generated successors
		for (int i = 0; i < asist.size(); i++) {
			asist.get(i).printContent();
			// System.out.printl(a[i]);
		}

		return;
	}
}
