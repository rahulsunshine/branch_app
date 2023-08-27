package com.ivl.suggestionsproject.entity;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "event_gallery")
public class EventGallery {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="eventName")
    private String eventName;

    @Column(name="eventDecription")
    private String eventDescription;

    @ManyToMany(fetch = FetchType.EAGER,cascade= CascadeType.ALL)
    @JoinTable(name="event_image_mapper",
            joinColumns = {
                    @JoinColumn(name = "eventGallery_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name="eventModel_id")
            }
    )
    private Set<ImageModel> eventImages;

//    @Column(name="date")
//    private Date date;

    public EventGallery() {
    }

    public EventGallery(String eventName, String eventDiscription, Set<ImageModel> eventImages) {
        this.eventName = eventName;
        this.eventDescription = eventDiscription;
        this.eventImages = eventImages;
//        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDiscription() {
        return eventDescription;
    }

    public void setEventDiscription(String eventDiscription) {
        this.eventDescription = eventDiscription;
    }

    public Set<ImageModel> getEventImages() {
        return eventImages;
    }

    public void setEventImages(Set<ImageModel> eventImages) {
        this.eventImages = eventImages;
    }

//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
}
