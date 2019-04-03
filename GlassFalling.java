/**
 * Glass Falling
 */
public class GlassFalling {

	// Do not change the parameters!
	public int glassFallingRecur(int floors, int sheets) {
		// If there are no floors, there will be no trials. If there is one floor, there will be one trial.
		if(floors == 1||floors == 0) {
			return floors;
		}
		// If there is one sheet, it will have trial=floor
		if(sheets == 1) {
			return floors;		// have to go through all floors
		}
		int min = Integer.MAX_VALUE, temp;
		for(int i=1; i<=floors; i++) {
			temp = Math.max(glassFallingRecur(i-1, sheets-1), glassFallingRecur(floors-i, sheets));
			min = Math.min(temp, min);
		}
		// + 1 because throwing a sheet counts as attempt
		return min + 1;
	}

	//Optional:
	//Pick whatever parameters you want to, just make sure to return an int.
	public int glassFallingMemoized(int floors, int sheets) {
		int[][] arr = new int[floors+1][sheets+1];
		for(int i=0; i<=floors; i++) {
			for(int j=0; j<=sheets; j++) {
				arr[i][j] = Integer.MAX_VALUE;
			}
		}
		return glassFallingMemoizedCont(floors, sheets, arr);
	}

	public int glassFallingMemoizedCont(int floors, int sheets, int[][] sheetDrops) {
		//If there are no floors, there will be no trials. If there is one floor, there will be one trial.
		if(floors == 1||floors == 0) {
			return floors;
		}

		//If there is one sheet, it will have trial=floor
		if(sheets == 1) {
			return floors;	// have to go through all floors
		}
	
		// if value was changed, return to use
		if(sheetDrops[floors][sheets] != Integer.MAX_VALUE) {
			return sheetDrops[floors][sheets];
		}
		
		int min = Integer.MAX_VALUE;
		int temp;
		for(int i=1; i<=floors; i++) {
			temp = Math.max(glassFallingMemoizedCont(i-1, sheets-1, sheetDrops), glassFallingMemoizedCont(floors-i, sheets, sheetDrops));
			min = Math.min(temp, min);
			//+1 because throwing a sheet counts as attempt
			sheetDrops[floors][sheets] = min + 1;
		}

		return sheetDrops[floors][sheets];
	}

	// Do not change the parameters!
	public int glassFallingBottomUp(int floors, int sheets) {
		int [][] sheetDrops = new int[floors+1][sheets+1];
		for(int i=1; i<=sheets; i++) {
			sheetDrops[0][i] = 0;
			sheetDrops[1][i] = 1;
		}
		for(int i=1; i<=floors; i++) {
			sheetDrops[i][1] = i;
		}
		for(int i=2; i<=sheets; i++) {
			for(int j=2; j<=floors; j++) {
				sheetDrops[j][i] = Integer.MAX_VALUE;
				int temp;
				for(int k=1; k<=j; k++) {
					temp = 1 + Math.max(sheetDrops[k-1][i-1], sheetDrops[j-k][i]);
					sheetDrops[j][i] = Math.min(temp, sheetDrops[j][i]);
				}
			}
		}
		return sheetDrops[floors][sheets];
	}


	public static void main(String args[]){
		GlassFalling gf = new GlassFalling();

		// Do not touch the below lines of code, and make sure
		// in your final turned-in copy, these are the only things printed
		int minTrials1Recur = gf.glassFallingRecur(27, 2);
		int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);
		int minTrials2Memo = gf.glassFallingMemoized(100, 3);
		int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
		System.out.println(minTrials1Recur + " " + minTrials1Bottom);
		System.out.println(minTrials2Memo + " " + minTrials2Bottom);
	}
}
