
/**
 * @author Matvey Skripchenko 
 * 
 * This class represents the shortest path computation 
 */

import java.io.FileNotFoundException;
import java.io.IOException;

public class FindShortestPath {

	/* Attribute declarations */

	Map droneMap; // initializing the map object

	/**
	 * This is the constructor for the class. It receives as input the name of the
	 * file containing the description of the city map, and the initial and
	 * destination map cells.
	 * 
	 * @param filename
	 *            map input file
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws InvalidMapException
	 */
	public FindShortestPath(String filename) throws IOException, FileNotFoundException, InvalidMapException {
		try {
			droneMap = new Map(filename);
		} catch (FileNotFoundException e) {
			System.out.println("The file with the specified name is not found, please try again");
		} catch (InvalidMapException e) {
			System.out.println("Specified map is wrong or does not exist, please try again");
		} catch (IOException e) {
			System.out.println("IOException");
		}

	}

	/**
	 * This method will create an object of the class FindShortestPath using the
	 * above constructor
	 * 
	 * @param args
	 *            the command line argument is the name of the input file
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws InvalidMapException
	 */
	public static void main(String[] args) throws IOException, FileNotFoundException, InvalidMapException {
		try {

			if (args.length < 1) {
				throw new IllegalArgumentException("Provide the name of the file with the input map");
			}

			FindShortestPath path = new FindShortestPath(args[0]);
			Map droneMap = new Map(args[0]);
			MapCell firstCell = droneMap.getUWOstore();
			DLPriorityQueue<MapCell> priorityQueue = new DLPriorityQueue<MapCell>();
			priorityQueue.enqueue(firstCell, 0); // adding the initial cell to the priority queue with zero priority
			firstCell.markEnqueued();
			MapCell smallestCell = firstCell;
			double totalDroneDistance;
			double droneDistance = 0;
			int count = 0;

			while (smallestCell.isCustomer() != true && !priorityQueue.isEmpty()) {
				if (smallestCell.isCustomer())
					smallestCell.markCustomer();

				smallestCell = (MapCell) priorityQueue.getSmallest(); // casting
				smallestCell.markDequeued();
				if (smallestCell.isCustomer())
					break;

				for (int i = 0; i < 6; i++) {
					MapCell neighbourCell = smallestCell.getNeighbour(i);

					if (neighbourCell != null && (path.interference(smallestCell.getNeighbour(i))))
						continue; // making sure the loop keeps on going if the above is true

					if (neighbourCell != null && !neighbourCell.isMarkedDequeued() && !neighbourCell.isNoFlying()) {
						droneDistance = 1 + smallestCell.getDistanceToStart();
						count = (int) droneDistance; // casting the count with the distance drone goes through
						if (droneDistance < neighbourCell.getDistanceToStart()) {
							neighbourCell.setDistanceToStart(count); //
							neighbourCell.setPredecessor(smallestCell);
						}

						totalDroneDistance = neighbourCell.getDistanceToStart()
								+ neighbourCell.euclideanDistToDest(droneMap);

						if (neighbourCell.isMarkedEnqueued()
								&& totalDroneDistance < priorityQueue.getPriority(neighbourCell)) {
							priorityQueue.changePriority(neighbourCell, totalDroneDistance);
						}

						if (!neighbourCell.isMarkedEnqueued()) {
							priorityQueue.enqueue(neighbourCell, totalDroneDistance);
							neighbourCell.markEnqueued();
						}
					}
				}
			}

			count += 1;
			if (smallestCell.isCustomer()) {
				System.out.println("It took " + count + " to reach the customer cell.");
			} else
				System.out.println("The shortest path to the cutomer cell has not been found.");

		} catch (FileNotFoundException e) {
			System.out.println("The file with the specified name is not found, please try again");
		} catch (InvalidMapException e) {
			System.out.println("Specified map is wrong or does not exist, please try again");
		} catch (IOException e) {
			System.out.println("IOException");
		}

	}

	/**
	 * This method returns the best cell for the drone to move to from the current
	 * one
	 * 
	 * @param cell
	 *            current cell for the drone
	 * @return boolean
	 * @throws InvalidNeighbourIndexException
	 */
	private boolean interference(MapCell cell) throws InvalidNeighbourIndexException {
		for (int i = 0; i < 6; i++) {

			if (cell.getNeighbour(i) != null) {

				if (cell.getNeighbour(i).isTower()) {
					return true;
				}
			}
		}
		return false;
	}
}
