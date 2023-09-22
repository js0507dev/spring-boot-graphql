package com.js0507dev.project1.auth.entity;

import com.js0507dev.project1.common.entity.AbstractTimestampEntity;
import com.js0507dev.project1.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "tokens")
public class Token extends AbstractTimestampEntity {
  @Id
  private String jwt;
  @Column(name = "member_id", insertable = false, updatable = false)
  private Long memberId;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id", referencedColumnName = "id")
  private Member member;
  @Column
  private Boolean revoked;
  @Column
  private String revokeReason;
}
