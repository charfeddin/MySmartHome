package com.example.rafafx;

import com.example.rafafx.entities.Comment;
import com.example.rafafx.entities.Post;
import com.example.rafafx.services.CommentService;
import com.example.rafafx.services.PostService;
import com.jfoenix.controls.JFXButton;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


public class PostItem implements Initializable {
    public VBox commentContainer;
    public JFXButton likeButton;
    public JFXButton dislikeButton;

    public JFXButton allLikesButton;

    private List<Integer> likedUserIds = new ArrayList<>();
    public JFXButton edit_button;
    public Label post_creation_date_label;
    public VBox user_detail_vbox;
    public JFXButton like_button;
    public JFXButton comment_button;
    public JFXButton share_button;
    public AnchorPane post_image_anchor;
    public ImageView post_image;
    public Label post_text_label;
    public Label post_title_label;
    public MenuButton options_menu_button;
    public TextArea comment_text_area;
    public JFXButton all_comment_button;
    private AnchorPane postCard; // Assuming your post card is an AnchorPane
    private VBox postContainer;
    private HelloController helloController;

    public PostService postService=new PostService();
    private int postId;
    private CommentService commentService=new CommentService();
    private IntegerProperty numLikesProperty = new SimpleIntegerProperty(0);

    public IntegerProperty numLikesProperty() {
        return numLikesProperty;
    }

    public int getNumLikes() {
        return numLikesProperty.get();
    }

    public void setNumLikes(int numLikes) {
        this.numLikesProperty.set(numLikes);
    }

    public void OnLikeButtonAction(ActionEvent actionEvent) {
//        if (likedUserIds.contains(userId)) {
//
//            likedUserIds.remove(userId);
//
//            like_button.setText(likedUserIds.size() + " Likes");
//
//        } else {
//
//            likedUserIds.add(userId);
//
//            like_button.setText(likedUserIds.size() + " Likes");
//
//        }



    }

    public void OnCommentButtonAction(ActionEvent actionEvent) {
    }

    public void OnReportButtonAction(ActionEvent actionEvent) {
    }

    public void OnRepostButtonAction(ActionEvent actionEvent) {
    }

    public void OnAllLikesButtonAction(ActionEvent actionEvent) {
    }

    public void OnAllCommentsButtonAction(ActionEvent actionEvent) {
        comment_text_area.setVisible(!comment_text_area.isVisible());
        commentContainer.setVisible(!commentContainer.isVisible());
        if(commentContainer.isVisible())
        {
            loadComments();

        }


    }

    public void OnUserInfoBarClicked(MouseEvent mouseEvent) {
    }

    public void OnAvatarCircleClicked(MouseEvent mouseEvent) {
    }

    public void OnRepostedButtonAction(ActionEvent actionEvent) {
    }

    public void OnEditButtonAction(ActionEvent actionEvent) {
    }

    public void OnShareButtonAction(ActionEvent actionEvent) {
    }

    public void setPost(Post post) {
        postId = post.getId();


        post_title_label.setText(post.getTitre());
        post_text_label.setText(post.getDescription());


        post_creation_date_label.setText(post.getDate().toString());


        String imageName = post.getImage();
        System.out.println(post.getImage());
        Path imagePath = Paths.get("C:/Users/Rafa Abdellaoui/Downloads/images", imageName);
        Image image = new Image(imagePath.toUri().toString());
        post_image.setImage(image);
    }

    public void editItemAction(ActionEvent actionEvent) throws IOException {


        FXMLLoader loder=new FXMLLoader();
        Stage master=new Stage();
        loder.setLocation(getClass().getResource(  "post-dialog.fxml"));
        loder.load();
        Parent root =loder.getRoot();
        Scene secene=new Scene(root, 800, 550);
        master.setTitle("Modifier Commande");
        PostDialog m=loder.getController();
        Post c1=postService.getTbyId(postId);

        System.out.println(c1);
        m.setUpdate("Modifier");
        m.setcommd(c1);


        m.SetVisibilite(false);
        master.centerOnScreen();
        master.setResizable(false);
        master.show();
        master.setScene(secene);
    }

    public void deleteItemAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to delete this post?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {

            if (postCard != null) {

                ((Pane) postCard.getParent()).getChildren().remove(postCard);
            }


            postService.supprimer(postId);


            if (helloController != null) {
                helloController.reloadPosts();
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allLikesButton.textProperty().bind(Bindings.concat(numLikesProperty).concat(" Likes"));




    }

    public void setHelloController(HelloController helloController) {
        this.helloController = helloController;
    }

    public void handleCommentKeyPress(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            String comment = comment_text_area.getText().trim();
            if (!comment.isEmpty()) {

                System.out.println("Comment submitted: " + comment);
                Comment c =new Comment();
                c.setContent(CommentService.filterInput(comment));
                Post p=postService.getTbyId(postId);
                c.setPost(p);
                commentService.ajouter(c);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);alert1.setHeaderText(null);
                alert1.setContentText("Comment Ajouté avec réussite");
                alert1.showAndWait();

                comment_text_area.clear();
            }
        }
    }
    private void loadComments() {
        List<Comment> comments = commentService.getCommentsForPost(postId);
        System.out.println(comments);


        commentContainer.getChildren().clear();


        for (Comment comment : comments) {
            Label commentLabel = new Label(comment.getContent());
            commentContainer.getChildren().add(commentLabel);


            ContextMenu contextMenu = new ContextMenu();


            MenuItem editMenuItem = new MenuItem("Edit");
            editMenuItem.setOnAction(event -> {
                // Handle edit action
                comment_text_area.setText(comment.getContent());

                // Add key listener to the comment text area
                comment_text_area.setOnKeyPressed(keyEvent -> {
                    if (keyEvent.getCode() == KeyCode.ENTER) {
                        String updatedContent = comment_text_area.getText().trim();
                        if (!updatedContent.isEmpty()) {
                            comment.setContent(updatedContent);
                            commentService.modifier(comment);
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                            alert1.setHeaderText(null);
                            alert1.setContentText("Updated avec success ");
                            alert1.showAndWait();
                            comment_text_area.clear();
                            commentContainer.setVisible(false);
                        } else {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setHeaderText(null);
                            alert.setContentText("Comment cannot be empty!");
                            alert.showAndWait();
                        }
                    }
                });




            });

            MenuItem deleteMenuItem = new MenuItem("Delete");
            deleteMenuItem.setOnAction(event -> {
                System.out.println(comment.getId());
                commentService.supprimer(comment.getId());
                commentContainer.setVisible(false);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);alert1.setHeaderText(null);
                alert1.setContentText("Supprimer avec success ");
                alert1.showAndWait();

            });

            // Add menu items to the context menu
            contextMenu.getItems().addAll(editMenuItem, deleteMenuItem);


            commentLabel.setContextMenu(contextMenu);
        }
    }

    public void likePostAction(ActionEvent actionEvent) {
        numLikesProperty.set(numLikesProperty.get() + 1);
    }

    public void dislikePostAction(ActionEvent actionEvent) {
        if (numLikesProperty.get() > 0) {
            numLikesProperty.set(numLikesProperty.get() - 1); // Decrement likes if greater than 0
        }
    }
}
