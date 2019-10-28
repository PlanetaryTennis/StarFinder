package relay;

import java.io.Serializable;
import java.util.Random;

import astronomy.Galaxy;
import utilities.ExtendedMathmatics;

public class RelayNetwork implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5106582796115738519L;

	private Galaxy myGalaxy;

	private PrimaryRelay[] myPrimes;
	private SecondaryRelay[] mySecondary;

	private SecondaryRelay[][] Pods;

	public Galaxy getMyGalaxy() {
		return myGalaxy;
	}

	public void setMyGalaxy(Galaxy myGalaxy) {
		this.myGalaxy = myGalaxy;
	}

	public PrimaryRelay[] getMyPrimes() {
		return myPrimes;
	}

	public void setMyPrimes(PrimaryRelay[] myPrimes) {
		this.myPrimes = myPrimes;
	}

	public SecondaryRelay[] getMySecondary() {
		return mySecondary;
	}

	public void setMySecondary(SecondaryRelay[] mySecondary) {
		this.mySecondary = mySecondary;
	}

	public SecondaryRelay[][] getPods() {
		return Pods;
	}

	public void setPods(SecondaryRelay[][] pods) {
		Pods = pods;
	}

	public static Random getRan() {
		return ran;
	}

	public static void setRan(Random ran) {
		RelayNetwork.ran = ran;
	}

	public RelayNetwork(Galaxy galaxy,double density){
		myGalaxy = galaxy;
		Generate(density);
	}

	public static Random ran = new Random(System.currentTimeMillis());

	private void Generate(double density) {
		if(density < 1)density = 1;

		int sectors = myGalaxy.getMySectors().size();
		int regions = 0;
		int odd = 0;
		for(int i = 0;i < myGalaxy.getMySectors().size();i++) {
			regions += myGalaxy.getMySectors().get(i).getRegions().size();
			if(ExtendedMathmatics.choose(myGalaxy.getMySectors().get(i).getRegions().size(),2) < myGalaxy.getMySectors().get(i).getRegions().size()*density) {
				odd += ExtendedMathmatics.choose(myGalaxy.getMySectors().get(i).getRegions().size(),2)-myGalaxy.getMySectors().get(i).getRegions().size()*density;
			}
		}

		Pods = new SecondaryRelay[regions][];
		myPrimes = new PrimaryRelay[(int) (sectors*2+(regions*density+odd)*2)];

		int zones = 0;
		for(int i = 0;i < myGalaxy.getMySectors().size();i++)
			for(int k = 0;k < myGalaxy.getMySectors().get(i).getRegions().size();k++)
				zones += myGalaxy.getMySectors().get(i).getRegions().get(k).getMyZones().size();

		mySecondary = new SecondaryRelay[zones];

		int sc = 0;
		int r = 0;
		for(int i = 0;i < myGalaxy.getMySectors().size();i++)
			for(int k = 0;k < myGalaxy.getMySectors().get(i).getRegions().size();k++) {
				Pods[r] = new SecondaryRelay[myGalaxy.getMySectors().get(i).getRegions()[k].getMyZones().size()];
				for(int l = 0;l < myGalaxy.getMySectors()[i].getRegions()[k].getMyZones().size();l++) {
					mySecondary[sc] = new SecondaryRelay();
					mySecondary[sc].setMyNetwork(this);
					mySecondary[sc].setTargetRegion(myGalaxy.getMySectors()[i].getRegions()[k]);
					mySecondary[sc].setMyZone(myGalaxy.getMySectors()[i].getRegions()[k].getMyZones()[l]);
					mySecondary[sc].setMySystem(myGalaxy.getMySectors()[i].getRegions()[k].getMyZones()[l].getMySystems()[ran.nextInt(myGalaxy.getMySectors()[i].getRegions()[k].getMyZones()[l].getMySystems().size())]);
					mySecondary[sc].setMyWorld(mySecondary[sc].getMySystem().getMyObjects()[ran.nextInt(mySecondary[sc].getMySystem().getMyObjects().size())]);
					mySecondary[sc].getMyWorld().getMySatilights().add(mySecondary[sc]);
					Pods[r][l] = mySecondary[sc];
					mySecondary[sc].setMyPod(Pods[r]);
					sc++;
				}
				r++;
			}

		//InterSectorPrimes
		int pc = 0;

		int b;
		int z;
		int s;
		int p;
		for(int i = 0;i < sectors;i++) {
			myPrimes[pc] = new PrimaryRelay();
			myPrimes[pc+1] = new PrimaryRelay();
			myPrimes[pc].setMyNetwork(this);
			myPrimes[pc+1].setMyNetwork(this);
			myPrimes[pc].setMyPartner(myPrimes[pc+1]);
			myPrimes[pc+1].setMyPartner(myPrimes[pc]);
			b = i+1;
			if(b >= sectors)b = 0;
			z = ran.nextInt(myGalaxy.getMySectors()[i].getRegions()[0].getMyZones().size());
			s = ran.nextInt(myGalaxy.getMySectors()[i].getRegions()[0].getMyZones()[z].getMySystems().size());
			p = ran.nextInt(myGalaxy.getMySectors()[i].getRegions()[0].getMyZones()[z].getMySystems()[s].getMyObjects().size());
			myPrimes[pc].setMySystem(myGalaxy.getMySectors()[i].getRegions()[0].getMyZones()[z].getMySystems()[s]);
			myPrimes[pc].setMyWorld(myGalaxy.getMySectors()[i].getRegions()[0].getMyZones()[z].getMySystems()[s].getMyObjects()[p]);
			r = ran.nextInt(myGalaxy.getMySectors()[b].getRegions().size());
			z = ran.nextInt(myGalaxy.getMySectors()[b].getRegions()[r].getMyZones().size());
			s = ran.nextInt(myGalaxy.getMySectors()[b].getRegions()[r].getMyZones()[z].getMySystems().size());
			p = ran.nextInt(myGalaxy.getMySectors()[b].getRegions()[r].getMyZones()[z].getMySystems()[s].getMyObjects().size());
			myPrimes[pc+1].setMySystem(myGalaxy.getMySectors()[b].getRegions()[r].getMyZones()[z].getMySystems()[s]);
			myPrimes[pc+1].setMyWorld(myGalaxy.getMySectors()[b].getRegions()[r].getMyZones()[z].getMySystems()[s].getMyObjects()[p]);
			myPrimes[pc].getMyWorld().getMySatilights().add(myPrimes[pc]);
			myPrimes[pc+1].getMyWorld().getMySatilights().add(myPrimes[pc+1]);
			pc++;pc++;
		}

		//IntraSector Primes
		int number = 0;
		int tempnum = 0;
		int[][] map;
		int[][] depth = new int[sectors][];
		for(int i = 0;i < sectors;i++) {
			number = (int) ((myGalaxy.getMySectors()[i].getRegions().size()*density-1)*2);
			tempnum = number/2;
			if(tempnum > ExtendedMathmatics.choose(myGalaxy.getMySectors()[i].getRegions().size(), 2)) tempnum = ExtendedMathmatics.choose(myGalaxy.getMySectors()[i].getRegions().size(), 2);
			number = tempnum * 2;
			map = mapOut(tempnum,myGalaxy.getMySectors()[i].getRegions().size());
			depth[i] = depthcluster(map, myGalaxy.getMySectors()[i].getRegions().size());
			for(int k = 0;k < map.size();k++) {
				myPrimes[pc] = new PrimaryRelay();
				myPrimes[pc+1] = new PrimaryRelay();
				myPrimes[pc].setMyNetwork(this);
				myPrimes[pc+1].setMyNetwork(this);
				myPrimes[pc].setMyPartner(myPrimes[pc+1]);
				myPrimes[pc+1].setMyPartner(myPrimes[pc]);
				r = map[k][0];
				z = ran.nextInt(myGalaxy.getMySectors()[i].getRegions()[r].getMyZones().size());
				s = ran.nextInt(myGalaxy.getMySectors()[i].getRegions()[r].getMyZones()[z].getMySystems().size());
				p = ran.nextInt(myGalaxy.getMySectors()[i].getRegions()[r].getMyZones()[z].getMySystems()[s].getMyObjects().size());
				myPrimes[pc].setMySystem(myGalaxy.getMySectors()[i].getRegions()[r].getMyZones()[z].getMySystems()[s]);
				myPrimes[pc].setMyWorld(myGalaxy.getMySectors()[i]
						.getRegions()[r]
								.getMyZones()[z]
										.getMySystems()[s]
												.getMyObjects()[p]);
				r = map[k][1];
				z = ran.nextInt(myGalaxy.getMySectors()[i].getRegions()[r].getMyZones().size());
				s = ran.nextInt(myGalaxy.getMySectors()[i].getRegions()[r].getMyZones()[z].getMySystems().size());
				p = ran.nextInt(myGalaxy.getMySectors()[i].getRegions()[r].getMyZones()[z].getMySystems()[s].getMyObjects().size());
				myPrimes[pc+1].setMySystem(myGalaxy.getMySectors()[i].getRegions()[r].getMyZones()[z].getMySystems()[s]);
				myPrimes[pc+1].setMyWorld(myGalaxy.getMySectors()[i].getRegions()[r].getMyZones()[z].getMySystems()[s].getMyObjects()[p]);
				myPrimes[pc].getMyWorld().getMySatilights().add(myPrimes[pc]);
				myPrimes[pc+1].getMyWorld().getMySatilights().add(myPrimes[pc+1]);
				pc++;pc++;
			}

		}

	}

	public static int[][] mapOut(int tempnum,int f) {
		int[][] out = new int[tempnum][2];

		int save = tempnum;

		if(tempnum == 1) {
			out[0][0] = 0;
			out[0][1] = 1;
			return out;
		}

		while(true) {
			tempnum = save;
			for(int i = 0;i < f&&i<tempnum;i++) {
				out[i][0] = i;
				out[i][1] = ran.nextInt(f);
				if(out[i][1] == i) i--;
			} 

			if(f != tempnum) {
				tempnum = tempnum-f;
				for(int i = 0;i < tempnum;i++) {
					out[f+i][0] = ran.nextInt(f);
					out[f+i][1] = ran.nextInt(f);
					if(out[f+i][0]==out[f+i][1])i--;
				}
			}



			if(nodup(out)&&valid(out,f)) {
				return out;

			}
		}
	}

	public static int[] depthcluster(int[][] in,int size) {
		int[] out = new int[size];
		out[0] = 0;
		for(int i = 1;i < size;i++) {
			out[i] = -13;
		}

		int[] count = new int[size];

		for(int i = 0;i < in.size();i++) {
			for(int k = 0;k < 2;k++) {
				for(int l = 0;l < size;l++) {
					if(in[i][k]==l) {
						count[l]++;
						break;
					}
				}
			}
		}

		int[][] measure = new int[size][];
		int tracker = 0;
		for(int i = 0;i < size;i++) {
			measure[i] = new int[count[i]];
			for(int k = 0;k < in.size();k++) {
				if(in[k][0]==i) {
					measure[i][tracker++]=in[k][1];
				}else if(in[k][1]==i) {
					measure[i][tracker++]=in[k][0];					
				}
			}
			tracker = 0;
		}

		//Pathing
		boolean keep = false;
		for(int look = 0;look < size;look++){
			if(out[look]!=-13)
				for(int i = 0;i < count[look];i++) {
					if(out[measure[look][i]]==-13) {
						out[measure[look][i]] = out[look]+1;
						keep = true;
					}
				}
			if(keep) {
				keep = false;
				look = 1;
			}
		}

		return out;
	}

	public static boolean valid(int[][] in, int f) {

		int[] tester = depthcluster(in,f);

		for(int i = 0;i < tester.size();i++) {
			if(tester[i]==-13) return false;
		}

		return true;
	}

	public static boolean nodup(int[][] in) {
		for(int i = 0;i < in.size();i++) {
			for(int k = i+1;k < inl;k++) {
				if(in[i][0]==in[k][0])
					if(in[i][1]==in[k][1]) {
						return false;
					}
				if(in[i][0]==in[k][1])
					if(in[i][1]==in[k][0]) {
						return false;
					}
			}
		}

		return true;
	}

}
