package editing;

import photo.Photograph;
import photo.Pixel;

/**
 * This class will be written by the Student.  It provides various
 * static methods that take a photograph and produce a copy of it with
 * various modifications.
 * 
 * See the project description for details of method implementations.
 * 
 * @author CMSC 131 Student
 *
 */
public class PhotoTools {

	//Creates a duplicate photograph object
	public static Photograph copy(Photograph photo) {
		Photograph newP = new Photograph(photo.getWidth(),photo.getHeight());

		for (int currentWidth = photo.getWidth()-1;currentWidth >= 0; currentWidth--){

			for (int currentHeight = photo.getHeight()-1;currentHeight >= 0; currentHeight--){
				Pixel current = photo.getPixel(currentWidth, currentHeight);
				newP.setPixel(currentWidth, currentHeight, current);


			}

		}

		//check is newP is identical
		return newP;
	}
	//First 10 rows are original, next ten are black horizontal stripes and so on
	public static Photograph vertStripe(Photograph photo) {
		Photograph newP = copy(photo);
		for(int Width = 0; Width<=photo.getWidth()-1; Width++){

			for(int Height = 0; Height< photo.getHeight();Height++) {
				if (Width%20 >= 10){
					Pixel black = new Pixel(0, 0, 0);
					newP.setPixel(Width, Height, black);
				}
			}

		}
		return newP;

	
}

public static Photograph horizStripe(Photograph photo) {

	Photograph newP = copy(photo);
	for(int Width = 0; Width<=photo.getWidth()-1; Width++){

		for(int Height = 0; Height< photo.getHeight();Height++) {
			if (Height%20 >= 10){
				Pixel black = new Pixel(0, 0, 0);
				newP.setPixel(Width, Height, black);
			}
		}

	}
	return newP;
}

public static Photograph isolateColor(Photograph photo, int type) {
	Photograph newP = new Photograph(photo.getWidth(),photo.getHeight());

	for (int currentWidth = photo.getWidth()-1;currentWidth >= 0; currentWidth--){
		for (int currentHeight = photo.getHeight()-1;currentHeight >= 0; currentHeight--){

			Pixel current = photo.getPixel(currentWidth, currentHeight);
			int red = current.getRed();
			int green = current.getGreen();
			int blue = current.getBlue();

			Pixel Red =  new Pixel(red, 0, 0);
			Pixel Green = new Pixel(0, green, 0);
			Pixel Blue = new Pixel(0, 0, blue);

			if(type == 0){
				newP.setPixel(currentWidth, currentHeight, Red);
			}	
			if(type == 1){
				newP.setPixel(currentWidth, currentHeight, Green);
			}
			if(type == 2){
				newP.setPixel(currentWidth, currentHeight, Blue);
			}
		}
	}
	return newP;
}

public static Photograph stretched(Photograph photo, int type) {
	Photograph doubleHigh = new Photograph(photo.getWidth(),(2*photo.getHeight()));
	Photograph doubleWide = new Photograph((2*photo.getWidth()),photo.getHeight());
	
	for (int currentWidth = photo.getWidth()-1;currentWidth >= 0; currentWidth--){
		for (int currentHeight = photo.getHeight()-1;currentHeight >= 0; currentHeight--){
			Pixel current = photo.getPixel(currentWidth, currentHeight);
			doubleWide.setPixel(2*currentWidth, currentHeight, current);
			doubleWide.setPixel(2*currentWidth+1, currentHeight, current);
		}
	}
	for (int currentWidth = photo.getWidth()-1;currentWidth >= 0; currentWidth--){
		for (int currentHeight = photo.getHeight()-1;currentHeight >= 0; currentHeight--){
			Pixel current = photo.getPixel(currentWidth, currentHeight);
			doubleHigh.setPixel(currentWidth, 2*currentHeight, current);
			doubleHigh.setPixel(currentWidth, 2*currentHeight+1, current);

		}
	}
	Photograph newP = new Photograph(photo.getWidth(),photo.getHeight());
	if (type==1){
		newP = doubleHigh;
	}else if(type==0){
		newP = doubleWide;
	}
	return newP;
}

public static Photograph enlargement(Photograph photo) {
	Photograph newP = new Photograph(photo.getWidth(),photo.getHeight());
	Photograph phase1 = stretched(photo, 0);
	Photograph phase2 = stretched(phase1, 1);
	newP = phase2;
	return newP;
}

public static Photograph rotated(Photograph photo) {
	Photograph newP = new Photograph(photo.getHeight(),photo.getWidth());


	for (int currentWidth = photo.getWidth()-1;currentWidth >= 0; currentWidth--){

		for (int currentHeight = photo.getHeight()-1;currentHeight >= 0; currentHeight--){
			int flip180degrees = photo.getHeight()-1;
			Pixel current = photo.getPixel(currentWidth, currentHeight);
			newP.setPixel(flip180degrees-currentHeight, currentWidth, current);

		}

	}

	return newP;
}



public static Photograph makeGrayScale(Photograph photo) {
	Photograph newP = new Photograph(photo.getWidth(),photo.getHeight());

	for (int currentWidth = photo.getWidth()-1;currentWidth >= 0; currentWidth--){
		for (int currentHeight = photo.getHeight()-1;currentHeight >= 0; currentHeight--){

			Pixel current = photo.getPixel(currentWidth, currentHeight);
			int red = current.getRed();
			int green = current.getGreen();
			int blue = current.getBlue();
			int avg = ((red + green + blue)/3);

			Pixel Avg =  new Pixel(avg, avg, avg);
			newP.setPixel(currentWidth, currentHeight, Avg);
		}
	}

	return newP;
}

public static Photograph weirdColorCombo(Photograph photo) {
	int widthBig = 0;
	int heightBig = 0;

	if (photo.getWidth()>photo.getHeight()){
		widthBig = photo.getHeight() + photo.getWidth() + photo.getHeight();
		heightBig = photo.getWidth();
	}else{
		widthBig = photo.getHeight() + photo.getWidth() + photo.getHeight();
		heightBig = photo.getHeight();
	}

	Photograph Big = new Photograph(widthBig,heightBig);



	Photograph redLeft = isolateColor(rotated(copy(photo)), 0);
	Photograph greenMiddle = isolateColor(rotated(rotated(copy(photo))), 1);
	Photograph blueRight = isolateColor(rotated(rotated(rotated(copy(photo)))), 2);

	for (int currentWidth = Big.getWidth()-1;currentWidth >= 0; currentWidth--){

		for (int currentHeight = Big.getHeight()-1;currentHeight >= 0; currentHeight--){
			Pixel current = Big.getPixel(currentWidth, currentHeight);
			Pixel black = new Pixel(0, 0, 0);
			Big.setPixel(currentWidth, currentHeight, black);


		}

	}

	for (int currentWidth = redLeft.getWidth()-1;currentWidth >= 0; currentWidth--){

		for (int currentHeight = redLeft.getHeight()-1;currentHeight >= 0; currentHeight--){
			Pixel current = redLeft.getPixel(currentWidth, currentHeight);
			Big.setPixel(currentWidth, currentHeight, current);

		}

	}

	for (int currentWidth = greenMiddle.getWidth()-1;currentWidth >= 0; currentWidth--){

		for (int currentHeight = greenMiddle.getHeight()-1;currentHeight >= 0; currentHeight--){
			Pixel current = greenMiddle.getPixel(currentWidth, currentHeight);
			Big.setPixel(currentWidth+(redLeft.getWidth()), currentHeight, current);

		}

	}

	for (int currentWidth = blueRight.getWidth()-1;currentWidth >= 0; currentWidth--){

		for (int currentHeight = blueRight.getHeight()-1;currentHeight >= 0; currentHeight--){
			Pixel current = blueRight.getPixel(currentWidth, currentHeight);
			Big.setPixel(currentWidth+(redLeft.getWidth()-1)+(greenMiddle.getWidth()-1)+2, currentHeight, current);

		}

	}





	return Big;
}
}
