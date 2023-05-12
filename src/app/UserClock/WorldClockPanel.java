package app.UserClock;
import java.awt.*;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class WorldClockPanel extends JPanel {

    private List<String> cityNames;
    private List<ZoneId> timeZones;
    private List<JLabel> clockLabels;

    public WorldClockPanel() {
        cityNames = new ArrayList<>();
        timeZones = new ArrayList<>();
        clockLabels = new ArrayList<>();

        // Aggiungi le città e i relativi fusi orari
        addCity("New York", "America/New_York");
        addCity("London", "Europe/London");
        addCity("Tokyo", "Asia/Tokyo");
        addCity("Sydney", "Australia/Sydney");
        addCity("Rome", "Europe/Rome");
        addCity("Paris", "Europe/Paris");
        addCity("Moscow", "Europe/Moscow");
        addCity("Dubai", "Asia/Dubai");
        addCity("Beijing", "Asia/Shanghai");
        addCity("Los Angeles", "America/Los_Angeles");

        setLayout(new BorderLayout());

        // Crea il pannello superiore per l'ora corrente
        JPanel currentTimePanel = new JPanel();
        currentTimePanel.setLayout(new BorderLayout());
        JLabel currentTimeLabel = new JLabel();
        currentTimeLabel.setFont(new Font("Arial", Font.BOLD, 48));
        currentTimePanel.add(currentTimeLabel, BorderLayout.CENTER);
        add(currentTimePanel, BorderLayout.NORTH);

        // Crea il pannello centrale per le diverse città
        JPanel cityPanel = new JPanel();
        cityPanel.setLayout(new GridLayout(cityNames.size(), 1));

        // Crea le etichette per le diverse città
        for (String city : cityNames) {
            JLabel clockLabel = new JLabel(city + ": ");
            clockLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            clockLabel.setFont(new Font("Arial", Font.PLAIN, 24));
            clockLabels.add(clockLabel);
            cityPanel.add(clockLabel);
        }

        add(cityPanel, BorderLayout.CENTER);

        // Aggiorna l'orario delle diverse città ogni secondo
        Timer updateTimer = new Timer(1000, e -> {
            LocalDateTime currentTime = LocalDateTime.now();
            currentTimeLabel.setText(currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            updateClocks();
        });
        updateTimer.start();
    }

    private void addCity(String cityName, String timeZoneId) {
        cityNames.add(cityName);
        timeZones.add(ZoneId.of(timeZoneId));
    }

    private void updateClocks() {
        for (int i = 0; i < cityNames.size(); i++) {
            ZoneId timeZone = timeZones.get(i);
            String cityName = cityNames.get(i);

            ZonedDateTime zonedDateTime = ZonedDateTime.now(timeZone);
            String time = zonedDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            clockLabels.get(i).setText(cityName + ": " + time);
        }
    }

}