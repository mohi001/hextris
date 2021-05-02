package hextris;

import hextris.shapes.Shape1;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
    int y = 0;
    Shape1 shape1 = new Shape1(3, 1);
    boolean pause = false;

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);
        Board board = new Board(root);
//                int[] p = s.getPoints();    Shape1 shape1 = new Shape1(7,1);
        root.getChildren().add(shape1);

        Timeline tl = new Timeline(new KeyFrame(Duration.seconds(1),
                e ->
                {
                    if (board.isValidMove(shape1, 0, 1))
                        shape1.moveDown();

                    if (board.isStop(shape1, 0, 1))
                    {
                        board.addToBoard(shape1);
                        shape1 = new Shape1(7, 2);
                        root.getChildren().add(shape1);
                        y = 0;
                    }
                }));
        System.out.println("ppppp");
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.P)
            {
                if (pause)
                {
                    tl.play();
                    pause = false;
                } else
                {
                    tl.pause();
                    pause = true;
                }
            }
            if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.W)
            {
                if (board.isValidRotate(shape1))
                    shape1.rotateCW();
            }
            if (e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.S)
            {
                if (board.isValidMove(shape1, 0, 1))
                    shape1.moveDown();
            }
            if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.D)
            {
                if (board.isValidMove(shape1, 1, 0))
                    shape1.moveRight();
            }
            if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.A)
            {
                if (board.isValidMove(shape1, -1, 0))
                    shape1.moveLeft();
            }
            if (e.getCode() == KeyCode.Q)
            {
                stage.close();
            }
        });


        //System.out.println(board.isFull(0,1));

        stage.setScene(scene);
        stage.setTitle("Hello World");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
