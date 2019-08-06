/*
 * this page gets displayed if the mean of the numbers is greater than 90 to call 
 * the mean and deviation method calls
 */
package managedBean;

public class WinningResult {

private  float mean = 0,standarddeviation = 0;

public float getMean() {
	return mean;
}

public void setMean(float mean) {
	this.mean = mean;
}

public float getStandarddeviation() {
	return standarddeviation;
}

public void setStandarddeviation(float standarddeviation) {
	this.standarddeviation = standarddeviation;
}


}
