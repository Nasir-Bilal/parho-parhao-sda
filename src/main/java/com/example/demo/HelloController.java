package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Separator;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Region;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import java.util.Optional;
import javafx.scene.control.TextInputDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;
import javafx.geometry.Pos;
public class HelloController {

    // === Fields from requestPage.fxml (or similar) ===
    @FXML
    private VBox requestsContainer;
    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<String> courseFilter;
    @FXML
    private ComboBox<String> uploadTimeFilter;
    @FXML
    private ComboBox<String> slotFilter;
    @FXML
    private Button notificationBtn;

    // === Fields from viewRequestPage.fxml ===
    @FXML private Label studentNameLabel;
    @FXML private Label courseLabel;
    @FXML private Label slotLabel;
    @FXML private Label daysLabel;
    @FXML private Label durationLabel;
    @FXML private Label postedDateLabel;
    @FXML private Label additionalInfoLabel;

    @FXML private ComboBox<String> slotComboBox;
    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker endDatePicker;
    @FXML private Spinner<Integer> startHourSpinner;
    @FXML private Spinner<Integer> startMinuteSpinner;
    @FXML private ComboBox<String> startAmPm;
    @FXML private Spinner<Integer> endHourSpinner;
    @FXML private Spinner<Integer> endMinuteSpinner;
    @FXML private ComboBox<String> endAmPm;

    @FXML private CheckBox monCheck;
    @FXML private CheckBox tueCheck;
    @FXML private CheckBox wedCheck;
    @FXML private CheckBox thuCheck;
    @FXML private CheckBox friCheck;
    @FXML private CheckBox satCheck;
    @FXML private CheckBox sunCheck;

    @FXML private Spinner<Double> feeSpinner;
    @FXML private TextArea messageTextArea;

    @FXML private Button sendOfferBtn;
    @FXML private Button backBtn;

    @FXML
    private VBox offersContainer;

    @FXML
    private ComboBox<String> sessionFilterComboBox;

    @FXML
    private GridPane sessionsGridPane;

    // Profile Page Fields
    @FXML
    private Label avatarInitials;
    @FXML
    private Label profileFullName;
    @FXML
    private Label profileUniversityId;
    @FXML
    private Button editProfileBtn;
    @FXML
    private TextField txtFullName;
    @FXML
    private TextField txtUniversityId;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtDepartment;
    @FXML
    private TextField txtDegreeProgram;
    @FXML
    private TextField txtSemester;
    @FXML
    private TextField txtCgpa;
    @FXML
    private TextField txtSpecialization;
    @FXML
    private TextField txtExperience;
    @FXML
    private TextArea txtProfileBio;
    @FXML
    private Label lblMemberSince;
    @FXML
    private Label lblLastUpdated;
    @FXML
    private HBox actionButtonsBox;
    @FXML
    private Label lblPhoneError;
    @FXML
    private Label lblCgpaError;
    @FXML
    private Label lblSemesterError;
    @FXML
    private Label lblExperienceError;
    // Navigation buttons
    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnRequests;
    @FXML
    private Button btnMyOffers;
    @FXML
    private Button btnSessions;
    @FXML
    private Button btnChat;
    @FXML
    private Button btnViewProfile;
    @FXML
    private Button btnLogout;

    // Current page tracking
    private String currentPage = "";

    private boolean isEditMode = false;



    @FXML
    public void initialize() {
        // Detect which page is currently loaded
        detectCurrentPage();
        updateActiveButton();
        // ========== Initialize Requests Page Fields (if present) ==========
        if (courseFilter != null) {
            courseFilter.getItems().addAll("Math", "Physics", "CS101");
        }
        if (uploadTimeFilter != null) {
            uploadTimeFilter.getItems().addAll("Today", "Last 3 days", "Last week");
        }
        if (slotFilter != null) {
            slotFilter.getItems().addAll("Morning", "Afternoon", "Evening");
        }
        if (notificationBtn != null) {
            notificationBtn.setOnAction(e -> System.out.println("Notification clicked"));
        }
        // ========== Initialize Requests Page Fields (if present) ==========
        if (requestsContainer != null) {

            // Filters
            if (courseFilter != null)
                courseFilter.getItems().addAll("Math", "Physics", "CS101");

            if (uploadTimeFilter != null)
                uploadTimeFilter.getItems().addAll("Today", "Last 3 days", "Last week");

            if (slotFilter != null)
                slotFilter.getItems().addAll("Morning", "Afternoon", "Evening");

            // Add dummy request cards using your formatted card UI
            for (int i = 1; i <= 5; i++) {
                requestsContainer.getChildren().add(buildRequestCard(
                        "Student " + i,
                        "Course " + i,
                        "Topic ABC " + i,
                        "Mon 2–4 PM",
                        "Need help understanding topic " + i,
                        "2025-11-21"
                ));
            }

            // Notification button click
            if (notificationBtn != null) {
                notificationBtn.setOnAction(e -> System.out.println("Notification clicked!"));
            }
        }

        // for offer Page
        if (offersContainer != null) {
            for (int i = 1; i <= 5; i++) {
                offersContainer.getChildren().add(buildOfferCard(
                        "Computer Networks Tutorial",
                        "Student " + i,
                        "2:00 PM",
                        "4:00 PM",
                        "Dec 5, 2024",
                        "Looking forward to the session! I have extensive experience teaching Computer Networks and can help you understand complex concepts.",
                        i % 2 == 0 ? "Pending" : "Accepted",
                        "Nov 22, 2024",
                        "PKR 1,000",
                        "PKR 500/hr",
                        "Online"
                ));
            }
        }

        // for sessions page
        // For Sessions Page
        if (sessionFilterComboBox != null && sessionsGridPane != null) {
            // Initialize filter dropdown
            sessionFilterComboBox.getItems().addAll(
                    "All Sessions",
                    "Upcoming",
                    "In Progress",
                    "Completed",
                    "Cancelled",
                    "Missed by Teacher",
                    "Missed by Student"
            );
            sessionFilterComboBox.setValue("All Sessions");

            // Add listener for filter changes
            sessionFilterComboBox.setOnAction(e -> {
                String selectedFilter = sessionFilterComboBox.getValue();
                loadSessionsByFilter(selectedFilter);
            });

            // Load initial sessions
            loadSessionsByFilter("All Sessions");
        }

        // ========== Initialize View Request / Offer Page Fields (if present) ==========
        if (slotComboBox != null) {
            slotComboBox.getItems().addAll(
                    "Morning (8:00 AM - 12:00 PM)",
                    "Afternoon (12:00 PM - 4:00 PM)",
                    "Evening (4:00 PM - 8:00 PM)",
                    "Night (8:00 PM - 10:00 PM)"
            );
        }

        if (startHourSpinner != null) {
            startHourSpinner.setValueFactory(
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, 9)
            );
        }
        if (endHourSpinner != null) {
            endHourSpinner.setValueFactory(
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, 10)
            );
        }
        if (startMinuteSpinner != null) {
            startMinuteSpinner.setValueFactory(
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0, 1)
            );
        }
        if (endMinuteSpinner != null) {
            endMinuteSpinner.setValueFactory(
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0, 1)
            );
        }

        if (startAmPm != null) {
            startAmPm.getItems().addAll("AM", "PM");
            startAmPm.setValue("AM");
        }
        if (endAmPm != null) {
            endAmPm.getItems().addAll("AM", "PM");
            endAmPm.setValue("AM");
        }

        if (feeSpinner != null) {
            feeSpinner.setValueFactory(
                    new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10000.0, 500.0, 50.0)
            );
        }

        if (sendOfferBtn != null) {
            sendOfferBtn.setOnAction(e -> handleSendOffer());
        }
        if (backBtn != null) {
            backBtn.setOnAction(e -> handleBackButton());
        }

        // Load dummy request details
        loadRequestDetails();


        // For Profile Page
        if (txtFullName != null) {
            loadProfileData();
        }
    }


    /**
     * Detect which page is currently loaded
     */
    private void detectCurrentPage() {
        if (btnDashboard != null && btnDashboard.getScene() != null) {
            // Try to detect from URL or set a default
            // This will be set properly when navigating
            currentPage = ""; // Will be set on first navigation
        }
    }

    private void showViewRequestPage() {
        // In a real app, you'd swap scene or container
        System.out.println("Switching to View Request page");
        loadRequestDetails();
    }

    private void loadRequestDetails() {
        if (studentNameLabel != null) studentNameLabel.setText("John Doe");
        if (courseLabel != null) courseLabel.setText("Computer Networks");
        if (slotLabel != null) slotLabel.setText("Evening (4:00 PM - 6:00 PM)");
        if (daysLabel != null) daysLabel.setText("Mon, Wed, Fri");
        if (durationLabel != null) durationLabel.setText("2 weeks");
        if (postedDateLabel != null) postedDateLabel.setText("Nov 20, 2024");
        if (additionalInfoLabel != null) additionalInfoLabel.setText(
                "Need help with OSI model and TCP/IP protocols."
        );
    }

    @FXML
    private void handleSendOffer() {
        // Validate required
        String selectedSlot = slotComboBox != null ? slotComboBox.getValue() : null;
        if (selectedSlot == null || selectedSlot.isEmpty()) {
            showAlert("Error", "Please select a time slot");
            return;
        }

        if (startDatePicker != null && startDatePicker.getValue() == null) {
            showAlert("Error", "Please select a start date");
            return;
        }
        if (endDatePicker != null && endDatePicker.getValue() == null) {
            showAlert("Error", "Please select an end date");
            return;
        }

        int startHour = startHourSpinner != null ? startHourSpinner.getValue() : 0;
        int startMinute = startMinuteSpinner != null ? startMinuteSpinner.getValue() : 0;
        String startPeriod = startAmPm != null ? startAmPm.getValue() : "";

        int endHour = endHourSpinner != null ? endHourSpinner.getValue() : 0;
        int endMinute = endMinuteSpinner != null ? endMinuteSpinner.getValue() : 0;
        String endPeriod = endAmPm != null ? endAmPm.getValue() : "";

        double fee = feeSpinner != null ? feeSpinner.getValue() : 0;
        String message = messageTextArea != null ? messageTextArea.getText() : "";

        System.out.println("=== OFFER ===");
        System.out.println("Slot: " + selectedSlot);
        System.out.println("Start: " + startHour + ":" + startMinute + " " + startPeriod);
        System.out.println("End: " + endHour + ":" + endMinute + " " + endPeriod);
        System.out.println("Fee: ₹" + fee);
        System.out.println("Message: " + message);

        showAlert("Success", "Your offer has been sent!");
    }

    @FXML
    private void handleBackButton() {
        System.out.println("Back button clicked");
        // In real app, swap back to requestPage
    }



    private HBox buildRequestCard(String student, String course, String topics,
                                  String slot, String details, String createdAt) {

        HBox card = new HBox();
        card.setSpacing(15);
        card.setPadding(new Insets(10));
        card.setStyle("-fx-background-color: rgba(255,255,255,0.08); -fx-background-radius: 10;");

        VBox info = new VBox(5);

        Hyperlink studentLink = new Hyperlink(student);
        studentLink.setStyle("-fx-text-fill: #4CC9F0; -fx-font-size: 14px;");
        studentLink.setOnAction(e -> showViewRequestPage()); // navigate to view page

        Label courseLabel = new Label("Course: " + course);
        Label topicsLabel = new Label("Topics: " + topics);
        Label slotLabel = new Label("Preferred: " + slot);

        if (details.length() > 40)
            details = details.substring(0, 40) + "...";

        Label detailsLabel = new Label("Details: " + details);
        Label createdLabel = new Label("Uploaded: " + createdAt);

        info.getChildren().addAll(
                studentLink,
                courseLabel,
                topicsLabel,
                slotLabel,
                detailsLabel,
                createdLabel
        );

        Button viewBtn = new Button("View");
        viewBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #7209B7, #4CC9F0); -fx-text-fill: white;");
        viewBtn.setPrefWidth(70);
        viewBtn.setOnAction(e -> showViewRequestPage()); // same navigation

        card.getChildren().addAll(info, viewBtn);
        return card;
    }

    private VBox buildOfferCard(String courseTitle, String studentName, String startTime,
                                String endTime, String date, String message,
                                String status, String offeredOn, String totalFee,
                                String feePerHour, String mode) {

        // Main Card Container
        VBox card = new VBox();
        card.setSpacing(15);
        card.setPadding(new Insets(25));
        card.setStyle("-fx-background-color: rgba(255,255,255,0.08);" +
                "-fx-background-radius: 15;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 15, 0, 0, 5);");
        card.getStyleClass().add("details-card");

        // Course Title
        Label titleLabel = new Label(courseTitle);
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");

        // Separator
        Separator separator = new Separator();
        separator.setStyle("-fx-background-color: rgba(255,255,255,0.2);");

        // GridPane for two-column layout
        GridPane grid = new GridPane();
        grid.setHgap(30);
        grid.setVgap(15);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        grid.getColumnConstraints().addAll(col1, col2);

        int row = 0;

        // LEFT COLUMN - Student (Clickable)
        Label studentTitleLabel = new Label("Student:");
        studentTitleLabel.getStyleClass().add("label-title");
        grid.add(studentTitleLabel, 0, row++);

        Hyperlink studentLink = new Hyperlink(studentName);
        studentLink.setStyle("-fx-text-fill: #4CC9F0; -fx-font-size: 14px; -fx-underline: true;");
        studentLink.setOnAction(e -> {
            // Handle student profile click
            System.out.println("View student profile: " + studentName);
        });
        grid.add(studentLink, 0, row++);

        // Session Date
        Label dateTitleLabel = new Label("Session Date:");
        dateTitleLabel.getStyleClass().add("label-title");
        grid.add(dateTitleLabel, 0, row++);

        Label dateLabel = new Label(date);
        dateLabel.getStyleClass().add("label-value");
        grid.add(dateLabel, 0, row++);

        // Time
        Label timeTitleLabel = new Label("Time:");
        timeTitleLabel.getStyleClass().add("label-title");
        grid.add(timeTitleLabel, 0, row++);

        Label timeLabel = new Label(startTime + " - " + endTime);
        timeLabel.getStyleClass().add("label-value");
        grid.add(timeLabel, 0, row++);

        // Mode
        Label modeTitleLabel = new Label("Mode:");
        modeTitleLabel.getStyleClass().add("label-title");
        grid.add(modeTitleLabel, 0, row++);

        Label modeLabel = new Label(mode);
        modeLabel.getStyleClass().add("label-value");
        grid.add(modeLabel, 0, row);

        // RIGHT COLUMN - Fee
        row = 0;
        Label feeTitleLabel = new Label("Fee:");
        feeTitleLabel.getStyleClass().add("label-title");
        grid.add(feeTitleLabel, 1, row++);

        Label feeLabel = new Label(totalFee + " (" + feePerHour + ")");
        feeLabel.setStyle("-fx-text-fill: #00FFA3; -fx-font-size: 16px; -fx-font-weight: bold;");
        grid.add(feeLabel, 1, row++);

        // Status
        Label statusTitleLabel = new Label("Status:");
        statusTitleLabel.getStyleClass().add("label-title");
        grid.add(statusTitleLabel, 1, row++);

        Label statusLabel = new Label(getStatusIcon(status) + " " + status);
        statusLabel.setStyle("-fx-text-fill: " + getStatusColor(status) + "; -fx-font-size: 14px; -fx-font-weight: bold;");
        grid.add(statusLabel, 1, row++);

        // Offered On
        Label offeredTitleLabel = new Label("Offered On:");
        offeredTitleLabel.getStyleClass().add("label-title");
        grid.add(offeredTitleLabel, 1, row++);

        Label offeredLabel = new Label(offeredOn);
        offeredLabel.getStyleClass().add("label-value");
        grid.add(offeredLabel, 1, row);

        // Message Section (Full Width)
        VBox messageBox = new VBox(8);
        messageBox.setPadding(new Insets(10, 0, 0, 0));

        Label messageTitleLabel = new Label("Your Message:");
        messageTitleLabel.getStyleClass().add("label-title");

        Label messageLabel = new Label(message);
        messageLabel.getStyleClass().add("label-value");
        messageLabel.setWrapText(true);
        messageLabel.setMaxWidth(Double.MAX_VALUE);

        messageBox.getChildren().addAll(messageTitleLabel, messageLabel);

        // Action Buttons
        VBox actionBox = new VBox(10);
        actionBox.setPadding(new Insets(15, 0, 0, 0));

        Button viewButton = new Button("View Details");
        viewButton.setPrefHeight(35);
        viewButton.setPrefWidth(150);
        viewButton.getStyleClass().add("back-btn");
        viewButton.setOnAction(e -> {
            // Handle view details
            System.out.println("View details clicked");
        });

        actionBox.getChildren().add(viewButton);

        // Add all components to card
        card.getChildren().addAll(titleLabel, separator, grid, messageBox, actionBox);

        return card;
    }

    // ==================== NAVIGATION METHODS ====================

    @FXML
    private void handleNavigateToDashboard() {
        navigateToPage("/com/example/demo/hello-view.fxml", "Dashboard");
    }

    @FXML
    private void handleNavigateToRequests() {
        navigateToPage("/com/example/demo/requestPage.fxml", "Requests");
    }

    @FXML
    private void handleNavigateToMyOffers() {
        navigateToPage("/com/example/demo/myOffersPage.fxml", "My Offers");
    }

    @FXML
    private void handleNavigateToSessions() {
        navigateToPage("/com/example/demo/sessionsPage.fxml", "Sessions");
    }

    @FXML
    private void handleNavigateToChat() {
        // TODO: Create chat page
        showAlert("Coming Soon", "Chat feature will be available soon!");
    }

    @FXML
    private void handleNavigateToProfile() {
        navigateToPage("/com/example/demo/ViewProfile.fxml", "View Profile");
    }

    @FXML
    private void handleLogout() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Are you sure you want to logout?");
        alert.setContentText("You will be redirected to the login page.");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // TODO: Clear session/user data
                System.out.println("Logging out...");
                // Navigate to login page or close application
                Stage stage = (Stage) btnLogout.getScene().getWindow();
                stage.close();
            }
        });
    }

    /**
     * Main navigation method that handles page transitions
     */
    private void navigateToPage(String fxmlPath, String pageTitle) {
        try {
            // Don't reload if already on this page
            if (currentPage.equals(fxmlPath)) {
                return;
            }

            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) btnDashboard.getScene().getWindow();

            // Create new scene with the loaded FXML
            Scene scene = new Scene(root, 900, 600);

            // Apply the same CSS stylesheet
            scene.getStylesheets().add(getClass().getResource("/com/example/demo/space-theme.css").toExternalForm());

            // Set the new scene
            stage.setScene(scene);
            stage.setTitle("Parho Parhao - " + pageTitle);

            // Update current page
            currentPage = fxmlPath;

            System.out.println("Navigated to: " + pageTitle);

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Navigation Error", "Could not load page: " + pageTitle);
        }
    }

    /**
     * Highlight the active navigation button based on current page
     */
    private void updateActiveButton() {
        // Remove active class from all buttons
        btnDashboard.getStyleClass().remove("sidebar-btn-active");
        btnRequests.getStyleClass().remove("sidebar-btn-active");
        btnMyOffers.getStyleClass().remove("sidebar-btn-active");
        btnSessions.getStyleClass().remove("sidebar-btn-active");
        btnChat.getStyleClass().remove("sidebar-btn-active");
        btnViewProfile.getStyleClass().remove("sidebar-btn-active");

        // Add active class to current button
        if (currentPage.contains("hello-view")) {
            btnDashboard.getStyleClass().add("sidebar-btn-active");
        } else if (currentPage.contains("requestPage")) {
            btnRequests.getStyleClass().add("sidebar-btn-active");
        } else if (currentPage.contains("myOffersPage")) {
            btnMyOffers.getStyleClass().add("sidebar-btn-active");
        } else if (currentPage.contains("sessionsPage")) {
            btnSessions.getStyleClass().add("sidebar-btn-active");
        } else if (currentPage.contains("ViewProfile")) {
            btnViewProfile.getStyleClass().add("sidebar-btn-active");
        }
    }

    // Helper method for status icon
    private String getStatusIcon(String status) {
        switch (status.toLowerCase()) {
            case "pending":
                return "⏳";
            case "accepted":
                return "✅";
            case "rejected":
                return "❌";
            default:
                return "⏳";
        }
    }

    // Helper method for status color
    private String getStatusColor(String status) {
        switch (status.toLowerCase()) {
            case "pending":
                return "#FFD700";
            case "accepted":
                return "#00FFA3";
            case "rejected":
                return "#FF6B6B";
            default:
                return "#FFD700";
        }
    }

    private void loadSessionsByFilter(String filter) {
        sessionsGridPane.getChildren().clear();

        // Sample data - Replace with actual database query
        List<SessionData> sessions = getSampleSessions();

        // Filter sessions based on selected filter
        List<SessionData> filteredSessions = sessions.stream()
                .filter(session -> filter.equals("All Sessions") || session.status.equals(filter))
                .collect(Collectors.toList());

        // Add sessions to grid (2 columns)
        int row = 0;
        int col = 0;

        for (SessionData session : filteredSessions) {
            VBox card = buildSessionCard(
                    session.courseName,
                    session.topic,
                    session.studentName,
                    session.studentEmail,
                    session.date,
                    session.startTime,
                    session.endTime,
                    session.duration,
                    session.feePerHour,
                    session.mode,
                    session.status
            );

            sessionsGridPane.add(card, col, row);

            col++;
            if (col > 1) {
                col = 0;
                row++;
            }
        }
    }

    private List<SessionData> getSampleSessions() {
        // Sample data - Replace with actual database query
        List<SessionData> sessions = new ArrayList<>();

        sessions.add(new SessionData("Computer Networks", "OSI Model & TCP/IP", "Ali Khan",
                "ali.khan@example.com", "Nov 25, 2024", "2:00 PM", "4:00 PM",
                "2 hours", "PKR 500", "Online", "Upcoming"));

        sessions.add(new SessionData("Data Structures", "Binary Trees", "Sara Ahmed",
                "sara.ahmed@example.com", "Nov 23, 2024", "10:00 AM", "12:00 PM",
                "2 hours", "PKR 600", "On-Campus", "Completed"));

        sessions.add(new SessionData("Database Systems", "SQL Queries", "Hassan Ali",
                "hassan.ali@example.com", "Nov 24, 2024", "3:00 PM", "5:00 PM",
                "2 hours", "PKR 550", "Online", "In Progress"));

        sessions.add(new SessionData("Web Development", "React Components", "Fatima Noor",
                "fatima.noor@example.com", "Nov 20, 2024", "4:00 PM", "6:00 PM",
                "2 hours", "PKR 700", "Online", "Cancelled"));

        sessions.add(new SessionData("Operating Systems", "Process Scheduling", "Ahmed Raza",
                "ahmed.raza@example.com", "Nov 22, 2024", "1:00 PM", "3:00 PM",
                "2 hours", "PKR 500", "On-Campus", "Missed by Student"));

        sessions.add(new SessionData("Machine Learning", "Neural Networks", "Ayesha Khan",
                "ayesha.khan@example.com", "Nov 21, 2024", "11:00 AM", "1:00 PM",
                "2 hours", "PKR 800", "Online", "Missed by Teacher"));

        return sessions;
    }

    // Helper class for session data
    private static class SessionData {
        String courseName, topic, studentName, studentEmail, date, startTime, endTime;
        String duration, feePerHour, mode, status;

        SessionData(String courseName, String topic, String studentName, String studentEmail,
                    String date, String startTime, String endTime, String duration,
                    String feePerHour, String mode, String status) {
            this.courseName = courseName;
            this.topic = topic;
            this.studentName = studentName;
            this.studentEmail = studentEmail;
            this.date = date;
            this.startTime = startTime;
            this.endTime = endTime;
            this.duration = duration;
            this.feePerHour = feePerHour;
            this.mode = mode;
            this.status = status;
        }
    }

    private VBox buildSessionCard(String courseName, String topic, String studentName,
                                  String studentEmail, String date, String startTime,
                                  String endTime, String duration, String feePerHour,
                                  String mode, String status) {

        // Main Card Container
        VBox card = new VBox();
        card.setSpacing(12);
        card.setPadding(new Insets(20));
        card.setStyle("-fx-background-color: rgba(255,255,255,0.08);" +
                "-fx-background-radius: 12;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 12, 0, 0, 4);");
        card.getStyleClass().add("details-card");
        card.setPrefHeight(280);

        // Course Name & Topic
        Label courseLabel = new Label(courseName);
        courseLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");

        Label topicLabel = new Label(topic);
        topicLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: rgba(255,255,255,0.7);");

        // Separator
        Separator separator = new Separator();
        separator.setStyle("-fx-background-color: rgba(255,255,255,0.15);");

        // Info Grid
        VBox infoBox = new VBox(8);

        // Student Name (Clickable)
        HBox studentBox = new HBox(5);
        Label studentTitleLabel = new Label("Student:");
        studentTitleLabel.getStyleClass().add("label-title");
        studentTitleLabel.setStyle("-fx-font-size: 12px;");

        Hyperlink studentLink = new Hyperlink(studentName);
        studentLink.setStyle("-fx-text-fill: #4CC9F0; -fx-font-size: 12px; -fx-underline: true;");
        studentLink.setOnAction(e -> {
            System.out.println("View student profile: " + studentName);
        });
        studentBox.getChildren().addAll(studentTitleLabel, studentLink);

        // Date & Time
        HBox dateTimeBox = new HBox(5);
        Label dateTimeLabel = new Label("📅 " + date + " | ⏰ " + startTime + " - " + endTime);
        dateTimeLabel.setStyle("-fx-text-fill: white; -fx-font-size: 12px;");
        dateTimeBox.getChildren().add(dateTimeLabel);

        // Duration & Mode
        HBox durationModeBox = new HBox(15);
        Label durationLabel = new Label("Duration: " + duration);
        durationLabel.getStyleClass().add("label-value");
        durationLabel.setStyle("-fx-font-size: 12px;");

        Label modeLabel = new Label("Mode: " + mode);
        modeLabel.getStyleClass().add("label-value");
        modeLabel.setStyle("-fx-font-size: 12px;");
        durationModeBox.getChildren().addAll(durationLabel, modeLabel);

        // Fee
        Label feeLabel = new Label("Fee: " + feePerHour + "/hr");
        feeLabel.setStyle("-fx-text-fill: #00FFA3; -fx-font-size: 13px; -fx-font-weight: bold;");

        // Status
        Label statusLabel = new Label(getSessionStatusIcon(status) + " " + status);
        statusLabel.setStyle("-fx-text-fill: " + getSessionStatusColor(status) +
                "; -fx-font-size: 12px; -fx-font-weight: bold;");

        infoBox.getChildren().addAll(studentBox, dateTimeBox, durationModeBox, feeLabel, statusLabel);

        // Contact Note
        Label contactNote = new Label("💌 Contact student via email: " + studentEmail);
        contactNote.setStyle("-fx-text-fill: rgba(255,255,255,0.6); -fx-font-size: 10px; -fx-font-style: italic;");
        contactNote.setWrapText(true);

        // Spacer
        Region spacer = new Region();
        VBox.setVgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

        // Action Button (only for upcoming sessions)
        HBox actionBox = new HBox();
        actionBox.setAlignment(Pos.CENTER);

        if (status.equals("Upcoming")) {
            Button cancelButton = new Button("Cancel Session");
            cancelButton.setPrefHeight(32);
            cancelButton.setPrefWidth(140);
            cancelButton.setStyle("-fx-background-color: #FF6B6B;" +
                    "-fx-text-fill: white;" +
                    "-fx-background-radius: 8;" +
                    "-fx-font-size: 12px;" +
                    "-fx-cursor: hand;");
            cancelButton.setOnAction(e -> {
                System.out.println("Cancel session: " + courseName);
                // Handle cancel session
            });
            actionBox.getChildren().add(cancelButton);
        }

        // Add all components to card
        card.getChildren().addAll(courseLabel, topicLabel, separator, infoBox, contactNote, spacer, actionBox);

        return card;
    }

    // Helper method for session status icon
    private String getSessionStatusIcon(String status) {
        switch (status) {
            case "Upcoming":
                return "📅";
            case "In Progress":
                return "▶️";
            case "Completed":
                return "✅";
            case "Cancelled":
                return "❌";
            case "Missed by Teacher":
            case "Missed by Student":
                return "⚠️";
            default:
                return "📅";
        }
    }

    // Helper method for session status color
    private String getSessionStatusColor(String status) {
        switch (status) {
            case "Upcoming":
                return "#4CC9F0";
            case "In Progress":
                return "#FFD700";
            case "Completed":
                return "#00FFA3";
            case "Cancelled":
                return "#FF6B6B";
            case "Missed by Teacher":
            case "Missed by Student":
                return "#FFA500";
            default:
                return "#4CC9F0";
        }
    }

    //sesions page
    // Profile Page Methods
    @FXML
    private void handleEditProfile() {
        if (!isEditMode) {
            // Enter edit mode
            isEditMode = true;
            editProfileBtn.setText("❌ Cancel Edit");
            actionButtonsBox.setVisible(true);

            // Make fields editable (except read-only ones)
            txtFullName.setEditable(true);
            txtPhone.setEditable(true);
            txtDepartment.setEditable(true);
            txtDegreeProgram.setEditable(true);
            txtSemester.setEditable(true);
            txtCgpa.setEditable(true);
            txtSpecialization.setEditable(true);
            txtExperience.setEditable(true);
            txtProfileBio.setEditable(true);

            // Change style to indicate editable
            setEditableStyle(txtFullName, true);
            setEditableStyle(txtPhone, true);
            setEditableStyle(txtDepartment, true);
            setEditableStyle(txtDegreeProgram, true);
            setEditableStyle(txtSemester, true);
            setEditableStyle(txtCgpa, true);
            setEditableStyle(txtSpecialization, true);
            setEditableStyle(txtExperience, true);
            txtProfileBio.setStyle("-fx-background-color: rgba(255,255,255,0.15); -fx-text-fill: white; " +
                    "-fx-background-radius: 8; -fx-padding: 10; -fx-border-color: #4CC9F0; " +
                    "-fx-border-radius: 8; -fx-border-width: 2;");

        } else {
            // Cancel edit mode
            handleCancelEdit();
        }
    }

    @FXML
    private void handleCancelEdit() {
        isEditMode = false;
        editProfileBtn.setText("✏️ Edit Profile");
        actionButtonsBox.setVisible(false);

        // Reset error messages
        hideErrorMessages();

        // Make fields non-editable
        txtFullName.setEditable(false);
        txtPhone.setEditable(false);
        txtDepartment.setEditable(false);
        txtDegreeProgram.setEditable(false);
        txtSemester.setEditable(false);
        txtCgpa.setEditable(false);
        txtSpecialization.setEditable(false);
        txtExperience.setEditable(false);
        txtProfileBio.setEditable(false);

        // Reset style
        setEditableStyle(txtFullName, false);
        setEditableStyle(txtPhone, false);
        setEditableStyle(txtDepartment, false);
        setEditableStyle(txtDegreeProgram, false);
        setEditableStyle(txtSemester, false);
        setEditableStyle(txtCgpa, false);
        setEditableStyle(txtSpecialization, false);
        setEditableStyle(txtExperience, false);
        txtProfileBio.setStyle("-fx-background-color: rgba(255,255,255,0.1); -fx-text-fill: white; " +
                "-fx-background-radius: 8; -fx-padding: 10; -fx-border-color: rgba(255,255,255,0.2); " +
                "-fx-border-radius: 8;");

        // Reload original data (from database in real app)
        loadProfileData();
    }

    @FXML
    private void handleSaveProfile() {
        // Validate all fields
        boolean isValid = validateProfileFields();

        if (!isValid) {
            showAlert("Validation Error", "Please fix the errors before saving.");
            return;
        }

        // Save to database (implement your database logic here)
        System.out.println("Saving profile...");
        System.out.println("Full Name: " + txtFullName.getText());
        System.out.println("Phone: " + txtPhone.getText());
        System.out.println("Department: " + txtDepartment.getText());
        System.out.println("Degree: " + txtDegreeProgram.getText());
        System.out.println("Semester: " + txtSemester.getText());
        System.out.println("CGPA: " + txtCgpa.getText());
        System.out.println("Specialization: " + txtSpecialization.getText());
        System.out.println("Experience: " + txtExperience.getText());
        System.out.println("Bio: " + txtProfileBio.getText());

        // Update header labels
        profileFullName.setText(txtFullName.getText());
        avatarInitials.setText(getInitials(txtFullName.getText()));

        // Exit edit mode
        handleCancelEdit();

        showAlert("Success", "Profile updated successfully!");
    }

    @FXML
    private void handleViewPublicProfile() {
        // Open public profile view (implement navigation to public profile page)
        System.out.println("Opening public profile view...");
        showAlert("Public Profile", "This will show how students see your profile.");
    }

    private void loadProfileData() {
        // Load from database (sample data for now)
        txtFullName.setText("Ahmed Khan");
        txtUniversityId.setText("i20-1234");
        txtPhone.setText("+92 300 1234567");
        txtEmail.setText("ahmed.khan@example.com");
        txtDepartment.setText("Computer Science");
        txtDegreeProgram.setText("BS Computer Science");
        txtSemester.setText("7");
        txtCgpa.setText("3.85");
        txtSpecialization.setText("Web Development, Data Structures, Algorithms");
        txtExperience.setText("18");
        txtProfileBio.setText("Passionate about teaching and helping students understand complex programming concepts. Experienced in web development, data structures, and algorithms.");

        profileFullName.setText("Ahmed Khan");
        profileUniversityId.setText("ID: i20-1234");
        avatarInitials.setText(getInitials("Ahmed Khan"));
        lblMemberSince.setText("November 15, 2023");
        lblLastUpdated.setText("November 20, 2024");
    }

    private boolean validateProfileFields() {
        boolean isValid = true;
        hideErrorMessages();

        // Validate Full Name (not empty)
        if (txtFullName.getText().trim().isEmpty()) {
            isValid = false;
            txtFullName.setStyle(txtFullName.getStyle() + "-fx-border-color: #FF6B6B;");
        }

        // Validate Phone (basic format)
        String phone = txtPhone.getText().trim();
        if (!phone.matches("^\\+?[0-9\\s-]{10,}$")) {
            lblPhoneError.setText("Invalid phone format");
            lblPhoneError.setVisible(true);
            isValid = false;
        }

        // Validate Semester (1-8)
        try {
            int semester = Integer.parseInt(txtSemester.getText().trim());
            if (semester < 1 || semester > 8) {
                lblSemesterError.setText("Semester must be between 1 and 8");
                lblSemesterError.setVisible(true);
                isValid = false;
            }
        } catch (NumberFormatException e) {
            lblSemesterError.setText("Semester must be a number");
            lblSemesterError.setVisible(true);
            isValid = false;
        }

        // Validate CGPA (0.0-4.0)
        try {
            double cgpa = Double.parseDouble(txtCgpa.getText().trim());
            if (cgpa < 0.0 || cgpa > 4.0) {
                lblCgpaError.setText("CGPA must be between 0.0 and 4.0");
                lblCgpaError.setVisible(true);
                isValid = false;
            }
        } catch (NumberFormatException e) {
            lblCgpaError.setText("CGPA must be a number");
            lblCgpaError.setVisible(true);
            isValid = false;
        }

        // Validate Experience (positive number)
        try {
            int experience = Integer.parseInt(txtExperience.getText().trim());
            if (experience < 0) {
                lblExperienceError.setText("Experience cannot be negative");
                lblExperienceError.setVisible(true);
                isValid = false;
            }
        } catch (NumberFormatException e) {
            lblExperienceError.setText("Experience must be a number");
            lblExperienceError.setVisible(true);
            isValid = false;
        }

        return isValid;
    }

    @FXML
    private void handleChangePassword() {
        // Create a custom dialog for password change
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Change Password");
        dialog.setHeaderText("Enter your new password");
        dialog.setContentText("New Password:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(password -> {
            // Update password in database
            System.out.println("New password: " + password);
            showAlert("Success", "Password changed successfully!");
        });
    }

    private void setupRealTimeValidation() {
        // Phone validation
        txtPhone.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("^\\+?[0-9\\s-]*$")) {
                txtPhone.setText(oldValue);
            }
        });

        // CGPA validation (only numbers and decimal point)
        txtCgpa.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*\\.?\\d*")) {
                txtCgpa.setText(oldValue);
            }
        });

        // Semester validation (only numbers)
        txtSemester.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtSemester.setText(oldValue);
            }
        });

        // Experience validation (only numbers)
        txtExperience.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtExperience.setText(oldValue);
            }
        });
    }
    private void hideErrorMessages() {
        lblPhoneError.setVisible(false);
        lblCgpaError.setVisible(false);
        lblSemesterError.setVisible(false);
        lblExperienceError.setVisible(false);
    }

    private void setEditableStyle(TextField field, boolean editable) {
        if (editable) {
            field.setStyle("-fx-background-color: rgba(255,255,255,0.15); -fx-text-fill: white; " +
                    "-fx-background-radius: 8; -fx-padding: 10; -fx-border-color: #4CC9F0; " +
                    "-fx-border-radius: 8; -fx-border-width: 2;");
        } else {
            field.setStyle("-fx-background-color: rgba(255,255,255,0.1); -fx-text-fill: white; " +
                    "-fx-background-radius: 8; -fx-padding: 10; -fx-border-color: rgba(255,255,255,0.2); " +
                    "-fx-border-radius: 8;");
        }
    }

    private String getInitials(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            return "??";
        }

        String[] parts = fullName.trim().split("\\s+");
        if (parts.length == 1) {
            return parts[0].substring(0, Math.min(2, parts[0].length())).toUpperCase();
        } else {
            return (parts[0].substring(0, 1) + parts[parts.length - 1].substring(0, 1)).toUpperCase();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
