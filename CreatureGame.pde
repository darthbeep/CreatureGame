//Creature c1, c2;
//Creature[] clist = new Creature[6];
ArrayList<Creature> creatures = new ArrayList();
int selectedNum = -1;
int breedNum = -1;
Creature mBreed, fBreed;
boolean once = true;
boolean selling = false;
void setup() {
  size(600, 600);
  background(255);
  PFont fn = loadFont("Monospaced-30.vlw");
  textFont(fn);
  setupReader();
  //c1 = new Creature();
  //c2 = new Creature();
  //generatePunnetSquare(c1, c2);
  //String s[] = PFont.list();
  //for (int i = 0; i < s.length; i++) System.out.println(s[i]);
  /*clist[0] = new Creature();
  clist[1] = new Creature();
  clist[2] = new Creature(clist[0], clist[1]);
  clist[3] = new Creature(clist[0], clist[1]);
  clist[4] = new Creature(clist[2], clist[3]);
  clist[5] = new Creature(clist[2], clist[3]);*/
  //for (int i = 0; i < 5; i++) creatures.add(new Creature());
  //housebackground();
}

void draw() {
  background(255);
  //generatePunnetSquare(c1, c2, mouseX, mouseY, "ha");
  //breedScreen(c1, c2);
  /*shape(clist[0].shape, 100, 100);
  shape(clist[1].shape, 300, 100);
  shape(clist[2].shape, 100, 300);
  shape(clist[3].shape, 300, 300);
  shape(clist[4].shape, 100, 500);
  shape(clist[5].shape, 300, 500);*/
  setupHouse();
  //newCreature();
  //storeSettings();
}
void mouseClicked() {
  clickCreature();
}
void keyPressed() {
  //save();
}
