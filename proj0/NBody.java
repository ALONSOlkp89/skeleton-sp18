public class NBody {
	public static double readRadius(String fileName) {
		In in = new In(fileName);

		int firstItemInFile = in.readInt();
		double secondItemInFile = in.readDouble();
		return secondItemInFile;
		// String thirdItemInFile = in.readString();
		// String fourthItemInFile = in.readString();
		// double fifthItemInFile = in.readDouble();
	}

	public static Body[] readBodies(String fileName) {
		In in = new In(fileName);

		int numPlanets = in.readInt();
		Body[] b = new Body[numPlanets];
		double radius = in.readDouble();
		for(int i = 0; i< numPlanets; i++) {
			b[i] = new Body(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
		}
		return b;

	}

	public static void main(String[] args) {

		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		double unitRadius = readRadius(filename);
		Body[] b = readBodies(filename);

		double time = 0;

		while(time <= T) {
			double[] xForces = new double[b.length];
			double[] yForces = new double[b.length];
			for(int i = 0; i < b.length; i++) {
				xForces[i] = b[i].calcNetForceExertedByX(b);
				yForces[i] = b[i].calcNetForceExertedByY(b);
			} 

			for(int i = 0; i < b.length; i++) {
				b[i].update(dt, xForces[i], yForces[i]);
			} 

			StdDraw.enableDoubleBuffering();
			StdDraw.setScale(-unitRadius, unitRadius);
			StdDraw.clear();
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for(int i = 0; i < b.length; i++) {
				b[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(15);
			time = time + dt;
		}

		StdOut.printf("%d\n", b.length);
		StdOut.printf("%.2e\n", unitRadius);
		for (int i = 0; i < b.length; i++) {
    			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                b[i].xxPos, b[i].yyPos, b[i].xxVel,
                b[i].yyVel, b[i].mass, b[i].imgFileName);   
		}
	}
}