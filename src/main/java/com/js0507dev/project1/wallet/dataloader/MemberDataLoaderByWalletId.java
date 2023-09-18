package com.js0507dev.project1.wallet.dataloader;

import com.js0507dev.project1.member.dto.MemberDTO;
import com.js0507dev.project1.member.service.MemberFetchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.BatchLoader;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class MemberDataLoaderByWalletId implements BatchLoader<Long, MemberDTO> {
  private final MemberFetchService memberFetchService;

  @Override
  public CompletionStage<List<MemberDTO>> load(List<Long> keys) {
    List<MemberDTO> members = memberFetchService.findByIds(keys);
    return CompletableFuture.completedFuture(keys.stream().map(key -> members.stream().filter(memberDTO -> Objects.equals(key, memberDTO.getId())).findFirst().orElseGet(null)).collect(Collectors.toList()));
  }
}
