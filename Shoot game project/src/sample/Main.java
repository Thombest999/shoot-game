package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.*;

public class Main extends Application {
    private Button button2;
    private TextField delete;
    private int[][] spawn = {{150,200},{50,100},{250}};
    AnimationTimer timer;
    private Pane root1 = new Pane();
    private Pane root2 = new Pane();
    private Pane root3 = new Pane();
    private int count = 0;
    private int score = 0;
    private ArrayList<Integer> scoreBoard;
    public Main() throws IOException {
    }
    private double t = 0;
    Scene scene3, scene4;
    private Text startGame = new Text(250, 50, "Start Game?");
    private Text yes = new Text(275, 200, "Yes");
    private Sprite player = new Sprite(300, 750, 40, 40, Color.LIGHTBLUE, "player");
    private Parent whatInside()
    {
        root2.setPrefSize(600, 800);
        player.setDead(false);
        root2.getChildren().add(player);
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
                player.moveLeft();
                player.moveRight();
                player.moveUp();
                player.moveDown();
            }
        };

        nextLevel();

        return root2;
    }
    public Color enemeySpawn()
    {
        Color[][] bruh = {{Color.DARKRED, Color.RED},{Color.ORANGERED, Color.INDIANRED}};
        int butWhy = (int)(Math.random() * 2);
        int butWhy2 = (int)(Math.random() * 2);
        for(int i = 0; i < bruh.length; i++)
        {
            for (int j = 0; j < bruh[i].length; j++) {
                String idk = "I will do incorporate this later";
            }
        }
        return bruh[butWhy][butWhy2];
    }
    public void nextLevel()
    {
        for (int i = 0; i < 5; i++) {
            Sprite s = new Sprite(90 + i * 100, 150, 40, 40, enemeySpawn(), "enemy");
            root2.getChildren().add(s);
        }
    }
    private List<Sprite> sprites()
    {
        return root2.getChildren().stream().map(n -> (Sprite)n).collect(Collectors.toList());
    }
    public void update()
    {
        t += 0.016;
        sprites().forEach(s -> {switch (s.type)
        {
            case "enemybullet":
                s.moveDown1();
                if(s.getBoundsInParent().intersects(player.getBoundsInParent()))
                {
                    player.dead = true;
                    s.dead = true;
                }
                break;
            case "playerbullet":
                s.moveUp1();
                sprites().stream().filter(e -> e.type.equals("enemy")).forEach(enemey -> {
                    if(s.getBoundsInParent().intersects(enemey.getBoundsInParent()))
                    {
                        enemey.dead = true;
                        s.dead = true;
                        count++;
                        score++;
                    }
                });
                break;
            case "enemy":
                if(t > 2)
                {
                    if(Math.random() < 0.5)
                    {
                        shoot(s);
                    }
                }
                break;
        }
        });
        root2.getChildren().removeIf(n -> {
            Sprite s =(Sprite) n;
            return s.dead;
        });
        if(t > 2)
        {
            t = 0;
        }
        if(count >= 5)
        {
            count = 0;
            nextLevel();
        }
        if(player.dead)
        {
            String printOut= "";
            timer.stop();
            root2.getChildren().add(button2);
            scoreBoard.add(scoreBoard.size(), score);
            for (int i = 0; i < scoreBoard.size(); i++) {
                printOut = printOut + scoreBoard.get(i) + ",";
            }
            Label yourScore = new Label(printOut);
            yourScore.setLayoutY(300);
            yourScore.setLayoutX(200);
            yourScore.setFont(new Font(20));
            Button textDelete = new Button("Which score do you want to delete");
            textDelete.setLayoutY(400);
            textDelete.setLayoutX(100);
            textDelete.setFont(new Font(20));
            root3.getChildren().add(textDelete);
            root3.getChildren().add(yourScore);
            delete = new TextField();
            delete.setLayoutX(200);
            delete.setLayoutY(500);
            root3.getChildren().add(delete);
            textDelete.setOnAction(e -> {
                for (int i = scoreBoard.size() - 1; i >= 0; i--) {
                    if(scoreBoard.get(i).toString().equals(delete.getText()))
                    {
                        scoreBoard.remove(i);
                    }
                }
                yourScore.setText(getWhatever(scoreBoard));
            });
        }
    }
    public String getWhatever(ArrayList<Integer> bruh)
    {
        String result = "";
        for (int i = 0; i < scoreBoard.size(); i++) {
            result = result + scoreBoard.get(i) + ",";
        }
        return result;
    }
    public void shoot(Sprite person)
    {
        Sprite s = new Sprite((int)person.getTranslateX() + 20, (int)person.getTranslateY(), 5, 20, Color.BLACK, person.type + "bullet");
        root2.getChildren().add(s);
    }
    private Parent menu()
    {
        root1.setPrefSize(600, 800);
        startGame.setFont(new Font(20));
        yes.setFont(new Font(15));
        root1.getChildren().add(startGame);
        return root1;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene5 = new Scene(menu());
        scoreBoard = new ArrayList<Integer>();
        scoreBoard.add(48);
        scoreBoard.add(38);
        scoreBoard.add(20);
        scoreBoard.add(56);
        Label label1= new Label("Hello this is the menu for the game click above to start");
        label1.setPrefSize(400,150);
        label1.setLayoutX(100);
        label1.setLayoutY(100);
        Button button1= new Button("Start Game?");
        Pane layout1 = new Pane();
        layout1.getChildren().addAll(button1, label1);
        button1.setFont(new Font(20));
        button1.setLayoutX(250);
        button1.setLayoutY(50);
        Scene scene1 = new Scene(layout1, 600, 800);
        Label label2= new Label("your score is the right most and others are previous score");
        label2.setFont(new Font(20));
        label2.setLayoutX(50);
        label2.setLayoutY(100);
        button2= new Button("View your score");
        Pane layout2= new Pane();
        root3.getChildren().addAll(label2, button2);
        Scene scene2 = new Scene(root3,600,800);
        button2.setOnAction(e -> primaryStage.setScene(scene2));
        Scene scene = new Scene(whatInside());
        button1.setOnAction(e -> {
            primaryStage.setScene(scene);
            timer.start();
        });
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode())
                {
                    case W:
                        player.setUp(true);
                        break;
                    case S:
                        player.setDown(true);
                        break;
                    case D:
                        player.setRight(true);
                        break;
                    case A:
                        player.setLeft(true);
                        break;
                    case SPACE:
                        shoot(player);
                        break;
                    default:
                        break;
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode())
                {
                    case A:
                        player.setLeft(false);
                        break;
                    case W:
                        player.setUp(false);
                        break;
                    case S:
                        player.setDown(false);
                        break;
                    case D:
                        player.setRight(false);
                        break;
                }
            }
        });
        primaryStage.setScene(scene1);
        primaryStage.show();
    }
    private static class Sprite extends Rectangle
    {
        boolean left;
        boolean right;
        boolean up;
        boolean down;
        boolean dead = false;
        final String type;
        Sprite(int x, int y, int w, int h, Color color, String type)
        {
            super(w, h, color);
            setTranslateX(x);
            setTranslateY(y);
            this.type = type;
        }
        public void setDead(boolean value)
        {
            dead = value;
        }
        public void setLeft(boolean lol)
        {
            left = lol;
        }
        public void setRight(boolean lol)
        {
            right = lol;
        }
        public void setUp(boolean lol)
        {
            up = lol;
        }
        public void setDown(boolean lol)
        {
            down = lol;
        }
        public void moveLeft()
        {
            if(left) {
                setTranslateX(getTranslateX() - 5);
            }
        }
        public void moveRight()
        {
            if(right) {
                setTranslateX(getTranslateX() + 5);
            }
        }
        public void moveUp()
        {
            if(up) {
                setTranslateY(getTranslateY() - 5);
            }
        }
        public void moveDown()
        {
            if(down) {
                setTranslateY(getTranslateY() + 5);
            }
        }
        public void moveUp1()
        {
            setTranslateY(getTranslateY() - 5);
        }
        public void moveDown1()
        {
            setTranslateY(getTranslateY() + 5);
        }
        public void moveRight1()
        {
            setTranslateX(getTranslateX() + 5);
        }
        public void moveLeft1()
        {
            setTranslateX(getTranslateX() - 5);
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
