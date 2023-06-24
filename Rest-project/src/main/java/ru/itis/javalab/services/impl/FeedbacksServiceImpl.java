package ru.itis.javalab.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.feedback.FeedbackDto;
import ru.itis.javalab.dto.feedback.FeedbacksPage;
import ru.itis.javalab.dto.feedback.NewFeedbackDto;
import ru.itis.javalab.exceptions.NotFoundException;
import ru.itis.javalab.models.Feedback;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.FeedbacksRepository;
import ru.itis.javalab.repositories.UsersRepository;
import ru.itis.javalab.services.FeedbacksService;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedbacksServiceImpl implements FeedbacksService {

    private final UsersRepository usersRepository;
    private final FeedbacksRepository feedbacksRepository;

    @Value("${default.page-size}")
    private int defaultPageCount;

    @Override
    public FeedbackDto addFeedback(NewFeedbackDto feedbackDto, Long userId) {
        Feedback feedback = Feedback.builder()
                .text(feedbackDto.getText())
                .score(feedbackDto.getScore())
                .feedbackTime(new Date())
                .state(Feedback.State.ACTIVE)
                .user(usersRepository.findById(userId)
                        .orElseThrow(() -> new NotFoundException("No user with id: <" + userId + "> found")))
                .build();

        feedbacksRepository.save(feedback);

        return FeedbackDto.from(feedback);
    }

    @Override
    public FeedbacksPage getAllFeedbacks(int numberOfPage, Long userId) {
        PageRequest pageRequest = PageRequest.of(numberOfPage, defaultPageCount);
        Page<Feedback> feedbackPage = feedbacksRepository.findAllByStateOrderById(pageRequest, Feedback.State.ACTIVE);

        return FeedbacksPage.builder()
                .feedbacks(FeedbackDto.from(feedbackPage.getContent()))
                .totalPagesCount(feedbackPage.getTotalPages())
                .build();
    }

    @Override
    public void delete(Long userId, Long feedbackId) {
        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("No user with id: <" + userId + "> found"));
        Feedback feedback = getOrThrow(feedbackId);

        user.getFeedbacks().remove(feedback);
        feedback.setState(Feedback.State.DELETED);
        feedbacksRepository.save(feedback);
    }

    @Override
    public FeedbackDto update(NewFeedbackDto feedbackDto, Long feedbackId, Long userId) {
        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("No user with id: <" + userId + "> found"));
        Feedback feedback = getOrThrow(feedbackId);

        feedback.setText(feedbackDto.getText());
        feedback.setScore(feedbackDto.getScore());

        return FeedbackDto.from(feedbacksRepository.save(feedback));
    }

    private Feedback getOrThrow(Long id) {
        return feedbacksRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Feedback with id <" + id + "> not found"));
    }
}
