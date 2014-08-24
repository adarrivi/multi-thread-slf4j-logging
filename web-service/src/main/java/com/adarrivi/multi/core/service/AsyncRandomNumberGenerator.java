package com.adarrivi.multi.core.service;

import java.util.List;
import java.util.concurrent.Future;

public interface AsyncRandomNumberGenerator {

    Future<List<Integer>> generateRandomNumbers();
}
