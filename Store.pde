//This file is about storing stuff in the save file and making the in game store work
//I think that's funny
//You should too
int money;
//Reads saved stuff
void setupReader() {
  /*BufferedReader reader;
  String line;
  reader = createReader("data.txt");*/
  String[] load = loadStrings("data/data.txt");
  String line;
  try {
    //line = reader.readLine();
    line = load[0];
    money = Integer.parseInt(line);
    //line = reader.readLine();
    line = load[1];
    int times = Integer.parseInt(line);
    for (int i = 0; i < times; i ++) {
      //System.out.println(times);
      //line = reader.readLine();
      line = load[2 + i];
      //System.out.println(line);
      creatures.add(new Creature(line));
      //System.out.println(creatures.get(i).name);
    }
  }
  catch (Exception e) {
    System.out.println("I have no idea what I'm doing.");
    line = "600";
  }
  //System.out.println(line);
  //money = Integer.parseInt(line);
}
//Save stuff
void save() {
    String[] saveString = new String[2 + creatures.size()];
    saveString[0] = money + "";
    saveString[1] = creatures.size() + "";
    for (int i = 0; i < creatures.size(); i++) {
      String thisLine = "";
      Creature saved = creatures.get(i);
      thisLine += saved.name + " " + saved.gender + " ";
      thisLine += saved.geneticDisease1 + " " + saved.geneticDisease2 + " ";
      thisLine += saved.friendliness + " " + saved.happiness + " " + saved.expensiveness + " ";
      thisLine += saved.speed + " " + saved.agility + " " + saved.attractiveness + " ";
      thisLine += saved.friendliness1 + " " + saved.happiness1 + " " + saved.expensiveness1 + " ";
      thisLine += saved.speed1 + " " + saved.agility1 + " " + saved.attractiveness1 + " ";
      thisLine += saved.friendliness2 + " " + saved.happiness2 + " " + saved.expensiveness2 + " ";
      thisLine += saved.speed2 + " " + saved.agility2 + " " + saved.attractiveness2 + " ";
      thisLine += ((int) red(saved.col)) + " " + ((int) green(saved.col)) + " " + ((int) blue(saved.col));
      saveString[2 + i] = thisLine;
    }
    /*PrintWriter output;
    output = createWriter("data.txt");
    output.print(saveString);
    output.flush();
    output.close();*/
    saveStrings("data/data.txt", saveString);
}

//Set up all the store stuff
void storeSettings( ) {
  //System.out.println("ayy lmao");
  displayMoney();
  savebutton();
  sellButton();
}

//Display amount of money on the screen
void displayMoney( ) {
  fill(0);
  textSize(20);
  text("Money: $" + money,10,585);
}
//You press this to save :D
void savebutton() {
    fill(#888888);
    rect(510, 555, 65, 40);
    fill(blackOrWhite(#888888));
    textSize(20);
    text("Save", 520, 585);
}
//The button that lets you sell stuff
void sellButton( ) {
  if (enviroment.equals("house")) {
    if (!selling) fill(80, 231, 18);
    else fill(color(14, 135, 32));
    rect(500, 5, 90, 40);
    fill(0);
    textSize(20);
    text("Sell",520,35);
  }
}
//Generates the store
void setupShop( ) {

}
//Shows how much a creature is worth
void showMoney( ) {
  if (enviroment.equals("house") && selling) {
    Creature youreGoingToGetSold = null;
    for (int i = 0; i < creatures.size(); i++) {
      float a = mouseX;
      float b = mouseY;
      float c = creatures.get(i).x;
      float d = creatures.get(i).y;
      if (a > c && a < c + 70 && b > d && b < d + 60) {
        youreGoingToGetSold = creatures.get(i);
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
      String value = "$"+price;
      float startTop = mouseY-10;
      float startLeft = mouseX;
      textSize(20);
      fill(youreGoingToGetSold.col);
      rect(startLeft,startTop, 8.5*value.length() + 30, 40);
      fill(blackOrWhite(youreGoingToGetSold.col));
      text(value,10+startLeft,startTop+30);
    }
  }
}
