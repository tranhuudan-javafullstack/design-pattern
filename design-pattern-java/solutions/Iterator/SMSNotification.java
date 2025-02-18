package Iterator;

import java.util.Iterator;
import java.util.ArrayDeque;
import java.util.Queue;

public class SMSNotification implements NotificationCollection {
    
    private Queue<Notification> smsNotifications;

    public SMSNotification() {
        smsNotifications = new ArrayDeque<>();
    }

    public void addNotification(String message) {
        smsNotifications.add(new Notification(message));
    }

    @Override
    public Iterator<Notification> createIterator() {
        return new SMSNotificationIterator(smsNotifications);
    }

    private class SMSNotificationIterator implements Iterator<Notification> {
        private Queue<Notification> notifications;

        public SMSNotificationIterator(Queue<Notification> notifications) {
            this.notifications = new ArrayDeque<>(notifications);
        }

        @Override
        public boolean hasNext() {
            return !notifications.isEmpty();
        }

        @Override
        public Notification next() {
            return notifications.poll();
        }
    }
}