package com.moelholm

import javax.persistence.Entity
import javax.persistence.Id
import javax.validation.constraints.NotNull

@Entity
class Message(@Id val id: String, @NotNull val text: String)