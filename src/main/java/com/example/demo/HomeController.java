package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class HomeController {

    // Search and Navigation
    // Post Request Page ComboBoxes
    // View Tutors Page Filters
    @FXML
    private ComboBox<String> cmbFilterSubject;

    @FXML
    private ComboBox<String> cmbFilterRating;

    @FXML
    private ComboBox<String> cmbFilterPrice;

    @FXML
    private ComboBox<String> cmbFilterAvailability;

    // My Bookings Page
    @FXML
    private ComboBox<String> cmbBookingFilter;
    @FXML
    private ComboBox<String> cmbCategory;

    @FXML
    private ComboBox<String> cmbDifficulty;

    @FXML
    private ComboBox<String> cmbDuration;

    @FXML
    private ComboBox<String> cmbTimePreference;

    @FXML
    private ComboBox<String> cmbSessionType;

    @FXML
    private ComboBox<String> cmbRequestDuration;

    @FXML
    private TextField txtSubject;

    @FXML
    private TextField txtBudgetMin;

    @FXML
    private TextField txtBudgetMax;

    @FXML
    private CheckBox chkMonday, chkTuesday, chkWednesday, chkThursday, chkFriday, chkSaturday, chkSunday;
    @FXML
    private TextField searchField;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnProfile;

    @FXML
    private Button btnPostRequest;

    @FXML
    private Button btnViewTutors;

    @FXML
    private Button btnMyBookings;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnLogout;

    // Profile Header Elements
    @FXML
    private Label lblProfileName;

    @FXML
    private Label lblStudentId;

    @FXML
    private Label lblMemberSince;

    @FXML
    private Label lblTotalSessions;

    @FXML
    private Label lblActiveRequestsProfile;

    @FXML
    private Button btnChangePhoto;

    @FXML
    private Button btnEditProfile;

    // Personal Information Elements
    @FXML
    private Label lblFullName;

    @FXML
    private Label lblAge;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblPhone;

    @FXML
    private Label lblDegree;

    @FXML
    private Label lblUniversity;

    @FXML
    private Button btnEditBasicInfo;

    @FXML
    private Button btnEditBio;

    @FXML
    private TextArea txtBioDescription;

    // Settings Elements
    @FXML
    private Button btnChangePassword;

    @FXML
    private Button btnEnable2FA;

    @FXML
    private CheckBox chkEmailNotifications;

    @FXML
    private CheckBox chkOfferAlerts;

    @FXML
    private CheckBox chkSessionReminders;

    @FXML
    private CheckBox chkMarketingEmails;

    @FXML
    private CheckBox chkProfileVisibility;

    @FXML
    private CheckBox chkShowReviews;

    @FXML
    private CheckBox chkSharePhone;

    @FXML
    private Button btnDeactivate;

    @FXML
    private Button btnDeleteAccount;

    @FXML
    private Button btnCancelSettings;

    @FXML
    private Button btnSaveSettings;

    // Track edit mode
    private boolean isEditingBasicInfo = false;
    private boolean isEditingBio = false;

    // Store text fields when editing
    private TextField txtFullName;
    private TextField txtAge;
    private TextField txtEmail;
    private TextField txtPhone;
    private TextField txtDegree;
    private TextField txtUniversity;

    // Store original values
    private String originalFullName;
    private String originalAge;
    private String originalEmail;
    private String originalPhone;
    private String originalDegree;
    private String originalUniversity;
    private String originalBio;

    @FXML
    public void initialize() {
        System.out.println("=== Controller initialized! ===");

        // Debug: Check if all components are loaded
        System.out.println("btnEditBasicInfo: " + (btnEditBasicInfo != null ? "LOADED" : "NULL"));
        System.out.println("btnEditBio: " + (btnEditBio != null ? "LOADED" : "NULL"));
        System.out.println("lblFullName: " + (lblFullName != null ? "LOADED" : "NULL"));
        System.out.println("lblAge: " + (lblAge != null ? "LOADED" : "NULL"));
        System.out.println("lblEmail: " + (lblEmail != null ? "LOADED" : "NULL"));
        System.out.println("lblPhone: " + (lblPhone != null ? "LOADED" : "NULL"));
        System.out.println("lblDegree: " + (lblDegree != null ? "LOADED" : "NULL"));
        System.out.println("lblUniversity: " + (lblUniversity != null ? "LOADED" : "NULL"));
        System.out.println("txtBioDescription: " + (txtBioDescription != null ? "LOADED" : "NULL"));

        // Store original values if labels exist
        if (lblFullName != null) {
            originalFullName = lblFullName.getText();
            originalAge = lblAge.getText();
            originalEmail = lblEmail.getText();
            originalPhone = lblPhone.getText();
            originalDegree = lblDegree.getText();
            originalUniversity = lblUniversity.getText();
            System.out.println("Original values stored");
        } else {
            System.out.println("WARNING: Labels are NULL - FXML not connected properly!");
        }

        if (txtBioDescription != null) {
            originalBio = txtBioDescription.getText();
        }

        System.out.println("=== Initialization complete ===");
    }

    // Toggle Basic Info Edit Mode
    @FXML
    public void toggleBasicInfoEdit() {
        System.out.println("========================================");
        System.out.println("toggleBasicInfoEdit() METHOD CALLED!");
        System.out.println("Current editing state: " + isEditingBasicInfo);
        System.out.println("btnEditBasicInfo is: " + (btnEditBasicInfo != null ? "NOT NULL" : "NULL"));
        System.out.println("lblFullName is: " + (lblFullName != null ? "NOT NULL" : "NULL"));
        System.out.println("========================================");

        if (btnEditBasicInfo == null) {
            System.out.println("ERROR: Button is NULL!");
            showAlert("Error", "Button not initialized! Check FXML fx:id", Alert.AlertType.ERROR);
            return;
        }

        if (lblFullName == null) {
            System.out.println("ERROR: Labels are NULL!");
            showAlert("Error", "Labels not initialized! Check FXML fx:id values", Alert.AlertType.ERROR);
            return;
        }

        if (!isEditingBasicInfo) {
            // Enter edit mode
            isEditingBasicInfo = true;
            btnEditBasicInfo.setText("💾 Save");
            btnEditBasicInfo.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

            // Get parent VBoxes
            VBox vboxFullName = (VBox) lblFullName.getParent();
            VBox vboxAge = (VBox) lblAge.getParent();
            VBox vboxEmail = (VBox) lblEmail.getParent();
            VBox vboxPhone = (VBox) lblPhone.getParent();
            VBox vboxDegree = (VBox) lblDegree.getParent();
            VBox vboxUniversity = (VBox) lblUniversity.getParent();

            // Create text fields
            txtFullName = createTextField(lblFullName.getText());
            txtAge = createTextField(lblAge.getText());
            txtEmail = createTextField(lblEmail.getText());
            txtPhone = createTextField(lblPhone.getText());
            txtDegree = createTextField(lblDegree.getText());
            txtUniversity = createTextField(lblUniversity.getText());

            // Hide labels and add text fields
            lblFullName.setVisible(false);
            lblFullName.setManaged(false);
            vboxFullName.getChildren().add(txtFullName);

            lblAge.setVisible(false);
            lblAge.setManaged(false);
            vboxAge.getChildren().add(txtAge);

            lblEmail.setVisible(false);
            lblEmail.setManaged(false);
            vboxEmail.getChildren().add(txtEmail);

            lblPhone.setVisible(false);
            lblPhone.setManaged(false);
            vboxPhone.getChildren().add(txtPhone);

            lblDegree.setVisible(false);
            lblDegree.setManaged(false);
            vboxDegree.getChildren().add(txtDegree);

            lblUniversity.setVisible(false);
            lblUniversity.setManaged(false);
            vboxUniversity.getChildren().add(txtUniversity);

            System.out.println("Entered edit mode");

        } else {
            // Save and exit edit mode
            isEditingBasicInfo = false;
            btnEditBasicInfo.setText("✏️ Edit");
            btnEditBasicInfo.setStyle("");

            // Update labels with new values
            lblFullName.setText(txtFullName.getText());
            lblAge.setText(txtAge.getText());
            lblEmail.setText(txtEmail.getText());
            lblPhone.setText(txtPhone.getText());
            lblDegree.setText(txtDegree.getText());
            lblUniversity.setText(txtUniversity.getText());

            // Update profile name in header
            if (lblProfileName != null) {
                lblProfileName.setText(txtFullName.getText());
            }

            // Initialize ComboBoxes for Post Request page
            if (cmbCategory != null) {
                cmbCategory.getItems().addAll("Computer Science", "Mathematics", "Physics", "Chemistry", "Biology", "Engineering", "Business", "Languages", "Other");
            }
            if (cmbDifficulty != null) {
                cmbDifficulty.getItems().addAll("Beginner", "Intermediate", "Advanced", "Expert");
            }
            if (cmbDuration != null) {
                cmbDuration.getItems().addAll("30 minutes", "1 hour", "1.5 hours", "2 hours", "2.5 hours", "3 hours");
            }
            if (cmbTimePreference != null) {
                cmbTimePreference.getItems().addAll("Morning (8 AM - 12 PM)", "Afternoon (12 PM - 5 PM)", "Evening (5 PM - 9 PM)", "Night (9 PM - 12 AM)", "Flexible");
            }
            if (cmbSessionType != null) {
                cmbSessionType.getItems().addAll("Online Only", "In-Person Only", "Both Online & In-Person");
            }
            if (cmbRequestDuration != null) {
                cmbRequestDuration.getItems().addAll("3 days", "1 week", "2 weeks", "1 month");
            }

            //------------------->
            // Initialize View Tutors filter ComboBoxes
            if (cmbFilterSubject != null) {
                cmbFilterSubject.getItems().addAll("All Subjects", "Computer Science", "Mathematics", "Physics", "Chemistry", "Biology");
            }
            if (cmbFilterRating != null) {
                cmbFilterRating.getItems().addAll("Any Rating", "4+ Stars", "4.5+ Stars", "5 Stars");
            }
            if (cmbFilterPrice != null) {
                cmbFilterPrice.getItems().addAll("Any Price", "Rs. 1000-2000/hr", "Rs. 2000-3000/hr", "Rs. 3000-5000/hr", "Rs. 5000+/hr");
            }
            if (cmbFilterAvailability != null) {
                cmbFilterAvailability.getItems().addAll("Any Time", "Weekdays", "Weekends", "Evenings");
            }

// Initialize My Bookings filter
            if (cmbBookingFilter != null) {
                cmbBookingFilter.getItems().addAll("All", "This Week", "This Month");
            }


            // Get parent VBoxes
            VBox vboxFullName = (VBox) lblFullName.getParent();
            VBox vboxAge = (VBox) lblAge.getParent();
            VBox vboxEmail = (VBox) lblEmail.getParent();
            VBox vboxPhone = (VBox) lblPhone.getParent();
            VBox vboxDegree = (VBox) lblDegree.getParent();
            VBox vboxUniversity = (VBox) lblUniversity.getParent();

            // Remove text fields and show labels
            vboxFullName.getChildren().remove(txtFullName);
            lblFullName.setVisible(true);
            lblFullName.setManaged(true);

            vboxAge.getChildren().remove(txtAge);
            lblAge.setVisible(true);
            lblAge.setManaged(true);

            vboxEmail.getChildren().remove(txtEmail);
            lblEmail.setVisible(true);
            lblEmail.setManaged(true);

            vboxPhone.getChildren().remove(txtPhone);
            lblPhone.setVisible(true);
            lblPhone.setManaged(true);

            vboxDegree.getChildren().remove(txtDegree);
            lblDegree.setVisible(true);
            lblDegree.setManaged(true);

            vboxUniversity.getChildren().remove(txtUniversity);
            lblUniversity.setVisible(true);
            lblUniversity.setManaged(true);

            // Show success message
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Profile information updated successfully!");
            successAlert.showAndWait();

            System.out.println("Saved and exited edit mode");
        }
    }

    // Helper method to create styled text field
    private TextField createTextField(String text) {
        TextField textField = new TextField(text);
        textField.setStyle("-fx-background-color: rgba(255, 255, 255, 0.1); -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 8; -fx-border-color: #64C8FF; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 5;");
        return textField;
    }

    // Toggle Bio Edit Mode
    @FXML
    public void toggleBioEdit() {
        System.out.println("Toggle Bio Edit clicked! Current mode: " + isEditingBio);

        if (!isEditingBio) {
            // Enter edit mode
            isEditingBio = true;
            btnEditBio.setText("💾 Save");
            btnEditBio.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

            // Make text area editable
            txtBioDescription.setEditable(true);
            txtBioDescription.setStyle("-fx-control-inner-background: rgba(255, 255, 255, 0.1); -fx-text-fill: white; -fx-border-color: #64C8FF; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 5;");

            System.out.println("Bio edit mode enabled");

        } else {
            // Save and exit edit mode
            isEditingBio = false;
            btnEditBio.setText("✏️ Edit");
            btnEditBio.setStyle("");

            // Make text area read-only again
            txtBioDescription.setEditable(false);
            txtBioDescription.setStyle("-fx-control-inner-background: transparent; -fx-text-fill: white; -fx-border-color: transparent;");

            // Update original bio
            originalBio = txtBioDescription.getText();

            // Show success message
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Bio updated successfully!");
            successAlert.showAndWait();

            System.out.println("Bio saved");
        }
    }

    // Change Photo
    @FXML
    public void changePhoto() {
        System.out.println("Change photo clicked");
        showAlert("Feature Coming Soon", "Photo upload functionality will be available soon!", Alert.AlertType.INFORMATION);
    }

    // Change Password
    @FXML
    public void changePassword() {
        System.out.println("Change password clicked");

        // Create a dialog for password change
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Change Password");
        dialog.setHeaderText("Enter your new password");

        // Set the button types
        ButtonType changeButtonType = new ButtonType("Change", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(changeButtonType, ButtonType.CANCEL);

        // Create password fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        PasswordField currentPassword = new PasswordField();
        currentPassword.setPromptText("Current Password");
        PasswordField newPassword = new PasswordField();
        newPassword.setPromptText("New Password");
        PasswordField confirmPassword = new PasswordField();
        confirmPassword.setPromptText("Confirm Password");

        grid.add(new Label("Current Password:"), 0, 0);
        grid.add(currentPassword, 1, 0);
        grid.add(new Label("New Password:"), 0, 1);
        grid.add(newPassword, 1, 1);
        grid.add(new Label("Confirm Password:"), 0, 2);
        grid.add(confirmPassword, 1, 2);

        dialog.getDialogPane().setContent(grid);

        // Show dialog and handle result
        dialog.showAndWait().ifPresent(response -> {
            if (response == changeButtonType) {
                if (newPassword.getText().equals(confirmPassword.getText())) {
                    showAlert("Success", "Password changed successfully!", Alert.AlertType.INFORMATION);
                } else {
                    showAlert("Error", "Passwords do not match!", Alert.AlertType.ERROR);
                }
            }
        });
    }

    // Save Settings
    @FXML
    public void saveSettings() {
        System.out.println("Save settings clicked");

        // Collect all settings
        boolean emailNotif = chkEmailNotifications.isSelected();
        boolean offerAlerts = chkOfferAlerts.isSelected();
        boolean sessionReminders = chkSessionReminders.isSelected();
        boolean marketingEmails = chkMarketingEmails.isSelected();
        boolean profileVisible = chkProfileVisibility.isSelected();
        boolean showReviews = chkShowReviews.isSelected();
        boolean sharePhone = chkSharePhone.isSelected();

        // Save to database or preferences
        System.out.println("Email Notifications: " + emailNotif);
        System.out.println("Offer Alerts: " + offerAlerts);
        System.out.println("Session Reminders: " + sessionReminders);
        System.out.println("Marketing Emails: " + marketingEmails);
        System.out.println("Profile Visible: " + profileVisible);
        System.out.println("Show Reviews: " + showReviews);
        System.out.println("Share Phone: " + sharePhone);

        showAlert("Success", "Settings saved successfully!", Alert.AlertType.INFORMATION);
    }

    // Cancel Settings
    @FXML
    public void cancelSettings() {
        System.out.println("Cancel settings clicked");
        showAlert("Cancelled", "Settings changes discarded.", Alert.AlertType.INFORMATION);
    }

    // Search functionality
    @FXML
    private void onSearchClick() {
        String query = searchField.getText();
        System.out.println("Searching for: " + query);
    }

    // Logout
    @FXML
    private void onLogoutClick() {
        System.out.println("Logout clicked");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Are you sure you want to logout?");
        alert.setContentText("You will be redirected to the login screen.");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                System.out.println("Logging out...");
                try {
                    // Navigate to login page
                    loadScene("studentLogin.fxml", "Login - Parha Do");
                } catch (Exception e) {
                    System.out.println("ERROR loading login page: " + e.getMessage());
                    e.printStackTrace();
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Navigation Error");
                    errorAlert.setContentText("Could not load login page: " + e.getMessage());
                    errorAlert.showAndWait();
                }
            }
        });
    }

    // Accept Offer
    @FXML
    private void onAcceptClick() {
        System.out.println("Accept clicked");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Accept Offer");
        alert.setHeaderText("Accept this tutoring offer?");
        alert.setContentText("This will create a confirmed booking.");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                showAlert("Success", "Offer accepted! Booking confirmed.", Alert.AlertType.INFORMATION);
            }
        });
    }

    // Decline Offer
    @FXML
    private void onDeclineClick() {
        System.out.println("Decline clicked");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Decline Offer");
        alert.setHeaderText("Decline this tutoring offer?");
        alert.setContentText("This action cannot be undone.");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                showAlert("Declined", "Offer has been declined.", Alert.AlertType.INFORMATION);
            }
        });
    }

    // Deactivate Account
    @FXML
    public void onDeactivateClick() {
        System.out.println("Deactivate clicked");
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Deactivate Account");
        alert.setHeaderText("Are you sure you want to deactivate your account?");
        alert.setContentText("Your account will be temporarily disabled. You can reactivate it anytime by logging in.");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                showAlert("Deactivated", "Your account has been deactivated.", Alert.AlertType.INFORMATION);
            }
        });
    }

    // Delete Account
    @FXML
    public void onDeleteClick() {
        System.out.println("Delete account clicked");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Delete Account");
        alert.setHeaderText("⚠️ DANGER: Delete Account");
        alert.setContentText("This will PERMANENTLY delete your account and all associated data. This action CANNOT be undone!");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // Second confirmation
                TextInputDialog inputDialog = new TextInputDialog();
                inputDialog.setTitle("Final Confirmation");
                inputDialog.setHeaderText("Type 'DELETE' to permanently delete your account");
                inputDialog.setContentText("Type DELETE:");

                inputDialog.showAndWait().ifPresent(input -> {
                    if ("DELETE".equals(input)) {
                        showAlert("Deleted", "Your account has been permanently deleted.", Alert.AlertType.INFORMATION);
                    } else {
                        showAlert("Cancelled", "Account deletion cancelled.", Alert.AlertType.INFORMATION);
                    }
                });
            }
        });
    }

    // Helper method to show alerts
    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

// Replace your existing loadScene method with this one:

    private void loadScene(String fxmlFile, String title) throws Exception {
        System.out.println("Loading scene: " + fxmlFile);

        // Try to get stage from ANY available control
        javafx.stage.Stage stage = null;

        // Check ALL possible controls (login page controls too!)
        if (btnLogin != null && btnLogin.getScene() != null) {
            stage = (javafx.stage.Stage) btnLogin.getScene().getWindow();
        } else if (txtLoginEmail != null && txtLoginEmail.getScene() != null) {
            stage = (javafx.stage.Stage) txtLoginEmail.getScene().getWindow();
        } else if (linkCreateAccount != null && linkCreateAccount.getScene() != null) {
            stage = (javafx.stage.Stage) linkCreateAccount.getScene().getWindow();
        } else if (btnSignup != null && btnSignup.getScene() != null) {
            stage = (javafx.stage.Stage) btnSignup.getScene().getWindow();
        } else if (linkBackToLogin != null && linkBackToLogin.getScene() != null) {
            stage = (javafx.stage.Stage) linkBackToLogin.getScene().getWindow();
        } else if (btnHome != null && btnHome.getScene() != null) {
            stage = (javafx.stage.Stage) btnHome.getScene().getWindow();
        } else if (btnProfile != null && btnProfile.getScene() != null) {
            stage = (javafx.stage.Stage) btnProfile.getScene().getWindow();
        } else if (btnLogout != null && btnLogout.getScene() != null) {
            stage = (javafx.stage.Stage) btnLogout.getScene().getWindow();
        } else if (searchField != null && searchField.getScene() != null) {
            stage = (javafx.stage.Stage) searchField.getScene().getWindow();
        }

        if (stage == null) {
            throw new Exception("Could not find stage - no UI controls initialized");
        }

        // Load the new FXML
        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                getClass().getResource(fxmlFile)
        );
        javafx.scene.Parent root = loader.load();

        // Create new scene with same dimensions as current
        javafx.scene.Scene scene = new javafx.scene.Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());

        // Set the new scene
        stage.setScene(scene);
        stage.setTitle(title);

        System.out.println("Scene loaded successfully: " + fxmlFile);
    }

    // Method for hello-view.fxml (if you're using that as your start page)
    @FXML
    private void onHelloButtonClick() {
        System.out.println("Hello button clicked!");
    }

    // ============================================
    // LOGIN PAGE HANDLERS
    // ============================================

    // Add these to your Login controller
    @FXML
    private TextField txtLoginEmail;

    @FXML
    private PasswordField txtLoginPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Hyperlink linkCreateAccount;

    @FXML
    private void onLoginButtonClick() {
        System.out.println("========== LOGIN BUTTON CLICKED ==========");

        // Get email and password (add validation as needed)
        String email = txtLoginEmail != null ? txtLoginEmail.getText() : "";
        String password = txtLoginPassword != null ? txtLoginPassword.getText() : "";

        System.out.println("Email: " + email);
        System.out.println("Password: " + (password.isEmpty() ? "empty" : "entered"));

        // Simple validation
        if (email.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setContentText("Please enter both email and password");
            alert.showAndWait();
            return;
        }

        // TODO: Add your authentication logic here
        // For now, just navigate to home page

        try {
            loadScene("homee.fxml", "Student Home - Parha Do");
        } catch (Exception e) {
            System.out.println("ERROR loading home page: " + e.getMessage());
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Navigation Error");
            alert.setContentText("Could not load home page: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void onCreateAccountClick() {
        System.out.println("========== CREATE ACCOUNT LINK CLICKED ==========");
        try {
            loadScene("studentSignup.fxml", "Sign Up - Parha Do");
        } catch (Exception e) {
            System.out.println("ERROR loading signup page: " + e.getMessage());
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Navigation Error");
            alert.setContentText("Could not load signup page: " + e.getMessage());
            alert.showAndWait();
        }
    }

    // ============================================
    // SIGNUP PAGE HANDLERS
    // ============================================

    @FXML
    private TextField txtSignupName;

    @FXML
    private TextField txtSignupEmail;

    @FXML
    private PasswordField txtSignupPassword;

    @FXML
    private PasswordField txtSignupConfirmPassword;

    @FXML
    private Button btnSignup;

    @FXML
    private Hyperlink linkBackToLogin;

    @FXML
    private void onSignupButtonClick() {
        System.out.println("========== SIGNUP BUTTON CLICKED ==========");

        // Get form data
        String name = txtSignupName != null ? txtSignupName.getText() : "";
        String email = txtSignupEmail != null ? txtSignupEmail.getText() : "";
        String password = txtSignupPassword != null ? txtSignupPassword.getText() : "";
        String confirmPassword = txtSignupConfirmPassword != null ? txtSignupConfirmPassword.getText() : "";

        // Validation
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Signup Error");
            alert.setContentText("Please fill in all fields");
            alert.showAndWait();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Signup Error");
            alert.setContentText("Passwords do not match");
            alert.showAndWait();
            return;
        }

        // TODO: Add your signup logic here (save to database, etc.)

        // Show success and go to login
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("Account created successfully! Please login.");
        alert.showAndWait();

        try {
            loadScene("studentLogin.fxml", "Login - Parha Do");
        } catch (Exception e) {
            System.out.println("ERROR loading login page: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void onBackToLoginClick() {
        System.out.println("========== BACK TO LOGIN CLICKED ==========");
        try {
            loadScene("studentLogin.fxml", "Login - Parha Do");
        } catch (Exception e) {
            System.out.println("ERROR loading login page: " + e.getMessage());
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Navigation Error");
            alert.setContentText("Could not load login page: " + e.getMessage());
            alert.showAndWait();
        }
    }

    // Navigation handlers
    @FXML
    private void onHomeClick() {
        System.out.println("========== HOME BUTTON CLICKED ==========");
        try {
            // Navigate to home page
            loadScene("homee.fxml", "Student Home - Parha Do");
        } catch (Exception e) {
            System.out.println("ERROR loading home page: " + e.getMessage());
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Navigation Error");
            alert.setContentText("Could not load home page: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void onProfileClick() {
        System.out.println("========== PROFILE BUTTON CLICKED ==========");
        try {
            // Navigate to profile page
            loadScene("StudentProfile.fxml", "My Profile - Parha Do");
        } catch (Exception e) {
            System.out.println("ERROR loading profile page: " + e.getMessage());
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Navigation Error");
            alert.setContentText("Could not load profile page: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void onPostRequestClick() {
        System.out.println("========== POST REQUEST BUTTON CLICKED ==========");
        try {
            loadScene("PostNewRequest.fxml", "Post New Request - Parha Do");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            showAlert("Navigation Error", "Could not load page: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onViewTutorsClick() {
        System.out.println("========== VIEW TUTORS CLICKED ==========");
        try {
            loadScene("ViewTutors.fxml", "View Tutors - Parha Do");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            showAlert("Navigation Error", "Could not load page: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onMyBookingsClick() {
        System.out.println("========== MY BOOKINGS CLICKED ==========");
        try {
            loadScene("MyBookings.fxml", "My Bookings - Parha Do");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            showAlert("Navigation Error", "Could not load page: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    @FXML
    private void onSettingsClick() {
        System.out.println("========== SETTINGS BUTTON CLICKED ==========");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Navigation");
        alert.setContentText("Scrolling to Settings tab...");
        alert.showAndWait();
    }

    @FXML
    private void onEditProfileClick() {
        System.out.println("========== EDIT PROFILE BUTTON CLICKED ==========");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setContentText("Use the individual Edit buttons to modify each section");
        alert.showAndWait();
    }

    @FXML
    private void onNotificationsClick() {
        System.out.println("========== NOTIFICATIONS BUTTON CLICKED ==========");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notifications");
        alert.setContentText("You have 4 new notifications");
        alert.showAndWait();
    }

    @FXML
    private void onSubmitRequestClick() {
        System.out.println("========== SUBMIT REQUEST CLICKED ==========");

        // Basic validation
        if (txtSubject != null && txtSubject.getText().isEmpty()) {
            showAlert("Validation Error", "Please enter a subject/topic", Alert.AlertType.ERROR);
            return;
        }

        // Show success message
        showAlert("Success", "Your tutoring request has been posted successfully!", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void onEnable2FAClick() {
        System.out.println("Enable 2FA clicked");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Enable Two-Factor Authentication");
        alert.setHeaderText("Enhance your account security");
        alert.setContentText("Would you like to enable two-factor authentication?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                showAlert("Success", "Two-factor authentication has been enabled!", Alert.AlertType.INFORMATION);
                if (btnEnable2FA != null) {
                    btnEnable2FA.setText("Disable");
                }
            }
        });
    }
}