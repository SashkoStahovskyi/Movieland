package com.stahovskyi.movieland.service.enrichment.result;


import com.stahovskyi.movieland.entity.Country;
import com.stahovskyi.movieland.entity.Genre;
import com.stahovskyi.movieland.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class EnrichmentResult {

    private List<Genre> genres;

    private List<Country> countries;

    private List<Review> reviews;

}
