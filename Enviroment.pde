String enviroment = "house";
void setupHouse() {
  //enviroment = "house";

  if (enviroment.equals("house")) housebackground();
  else if (enviroment.equals("breed")) {
    breedScreen(mBreed, fBreed);
  }
  else if (enviroment.equals("new")) newCreature();
}
//Lets you learn about a creature by mousing over it.
void profile() {
  if (enviroment.equals("house")) {
    textSize(30);
    int startTop = 0;
    int startLeft = 0;
    Creature selected = null;
    for (int i = 0; i < creatures.size(); i++) {
      int a = mouseX;
      int b = mouseY;
      startTop = b-200;
      if (startTop < 0) startTop = 0;
      if (a < 400) startLeft = a;
      else startLeft = a-200;
      float c = creatures.get(i).x;
      float d = creatures.get(i).y;
      //selected = null;
      if (a > c && a < c + 70 && b > d && b < d + 60) {
        selected = creatures.get(i);
      }
      creatures.get(i).box();
    }
    if (selected != null) {
      displayProfile(selected, startLeft, startTop);
      //friendliness happiness expensiveness
      //speed agility attractiveness
      //selected.selected = true;
      /*fill(selected.col);
      rect(startLeft, startTop, 200, 185);
      fill(0);
      //rect(50, 50, 17, 50);
      text(selected.name, startLeft + 100 - 8.5*(selected.name.length()), startTop+30);
      textSize(20);
      int shift = 20;
      if (selected.gender) text("Female", startLeft + 66, startTop + 50);
      else text("Male", startLeft + 78, startTop + 50);
      text("Friendliness", startLeft+5, startTop + 60 + shift);
      text(((int) selected.getFriendliness()) + "", startLeft + 172.5, startTop+60 + shift);
      text("Happiness", startLeft+5, startTop + 80 + shift);
      text(((int) selected.getHappiness()) + "", startLeft + 172.5, startTop+80 + shift);
      text("Expensiveness", startLeft+5, startTop + 100 + shift);
      text(((int) selected.getExpensiveness()) + "", startLeft + 172.5, startTop+100 + shift);
      text("Speed", startLeft+5, startTop + 120 + shift);
      text(((int) selected.getSpeed()) + "", startLeft + 172.5, startTop+120 + shift);
      text("Agility", startLeft+5, startTop + 140 + shift);
      text(((int) selected.getAgility()) + "", startLeft + 172.5, startTop+140 + shift);
      text("Attractiveness", startLeft+5, startTop + 160 + shift);
      text(((int) selected.getAttractiveness()) + "", startLeft + 172.5, startTop+160 + shift);
    */}
  }
}
//When the creature is clicked
void clickCreature() {
  //Save button
  if (mouseX > 510 && mouseX < 575 && mouseY > 555 && mouseY < 595) {
    fill(#dd123c);
    rect(510, 555, 65, 40);
    delay(100);
    save();
  }
  //If the breed button has been clicked
  if (enviroment.equals("breed")) {
    //The cancel button
    if (mouseX > 350 && mouseX < 350 + 150 && mouseY > 425 && mouseY < 525) {
      breedNum = -1;
      selectedNum = -1;
      enviroment = "house";
    }
    //calculates the price
    int breedCost = (getCreatureValue(mBreed) + getCreatureValue(fBreed))/2;
    //The breed creatures button
    if (mouseX > 100 && mouseX < 300 && mouseY > 425 && mouseY < 525 && money-breedCost >= 0) {
      breedNum = -1;
      selectedNum = -1;
      enviroment = "new";
      creatures.add(new Creature(mBreed, fBreed));
      money -= (getCreatureValue(mBreed) + getCreatureValue(fBreed))/2;
      newCreature();
    }
  }
  else if (enviroment.equals("house") && selling) {
    Creature youreGoingToGetSold = null;
    int terminated = -1;
    for (int i = 0; i <creatures.size();i++) {
      float a = mouseX;
      float b = mouseY;
      float c = creatures.get(i).x;
      float d = creatures.get(i).y;
      if (a > c && a < c + 70 && b > d && b < d + 60) {
        youreGoingToGetSold = creatures.get(i);
        terminated = i;
      }
    }
    if (youreGoingToGetSold != null) {
      int price = getCreatureValue(youreGoingToGetSold);
      /*price += youreGoingToGetSold.getFriendliness();
      price += youreGoingToGetSold.getHappiness();
      price += youreGoingToGetSold.getExpensiveness();
      price += youreGoingToGetSold.getSpeed();
      price += youreGoingToGetSold.getAgility();
      price += youreGoingToGetSold.getAttractiveness();*/
      money += price;
      creatures.remove(terminated);
    }
    else {
      selling = false;
    }
  }
  //If the enviroment is house and you aren't selling stuff
  else if (enviroment.equals("house") && !selling) {
    //Click the sell button
    if (mouseX > 500 && mouseX < 590 && mouseY > 5 && mouseY < 45) {
      selling = true;
    }
    else {
      selling = false;
    }
    //If looking for someone to breed with
    if (breedNum != -1) {
      for (int i = 0; i < creatures.size(); i++) {
        int a = mouseX;
        int b = mouseY;
        float c = creatures.get(i).x;
        float d = creatures.get(i).y;
        //selected = null;
        creatures.get(i).selected = false;
        if (a > c && a < c + 70 && b > d && b < d + 60) {
          selectedNum = i;
        }
      }
      if (selectedNum != -1) {
        mBreed = creatures.get(breedNum);
        fBreed = creatures.get(selectedNum);
        if (xor(mBreed.gender, fBreed.gender)) {
          enviroment = "breed";
          //System.out.print("help");
          //breedScreen(m, f);
        }
      }
    }
    //If someone has been selected
    if (selectedNum != -1) {
      breedNum = -1;
      creatures.get(selectedNum).breeding = false;
      //for (int i = 0; i < creatures.size(); i++) {
        int a = mouseX;
        int b = mouseY;
        float c = creatures.get(selectedNum).x;
        float d = creatures.get(selectedNum).y;
        //selected = null;
        //creatures.get(i).selected = false;
        if (a > c && a < c + 70 && b > d + 60 && b < d + 60 + 18) breedNum=selectedNum;
      //}
      //rect(c, d, 20, 20);
      if (breedNum != -1)  {
        creatures.get(selectedNum).breeding = true;
      }
      if (breedNum == -1) selectedNum = -1;
    }
    //If no one has been selected
    if (selectedNum == -1) {
      selectedNum = -1;
      breedNum = -1;
      for (int i = 0; i < creatures.size(); i++) {
        int a = mouseX;
        int b = mouseY;
        float c = creatures.get(i).x;
        float d = creatures.get(i).y;
        //selected = null;
        creatures.get(i).selected = false;
        if (a > c && a < c + 70 && b > d && b < d + 60) {
          selectedNum = i;
        }
        //if (a > c && a < c + 70 && b > d + 60 && b < d + 60 + 18) rect(20, 20, 20, 20);
      }
      if (selectedNum != -1) {
        /*System.out.println(red(creatures.get(selectedNum).col));
        System.out.println(green(creatures.get(selectedNum).col));
        System.out.println(blue(creatures.get(selectedNum).col));*/
        creatures.get(selectedNum).selected = true;
      }
    }
  }
  else if (enviroment.equals("new")) {
    if (mouseX > 100 && mouseX < 500 && mouseY > 400 && mouseY < 500) {
      enviroment = "house";
      //System.out.println(1);
    }
  }


}
