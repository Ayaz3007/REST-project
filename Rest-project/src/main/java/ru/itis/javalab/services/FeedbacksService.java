package ru.itis.javalab.services;

import ru.itis.javalab.dto.feedback.FeedbackDto;
import ru.itis.javalab.dto.feedback.FeedbacksPage;
import ru.itis.javalab.dto.feedback.NewFeedbackDto;

public interface FeedbacksService {
    FeedbackDto addFeedback(NewFeedbackDto feedbackDto, Long userId);

    FeedbacksPage getAllFeedbacks(int numberOfPage, Long userId);

    void delete(Long userId, Long feedbackId);

    FeedbackDto update(NewFeedbackDto feedbackDto, Long feedbackId, Long userId);
}
