package com.moelholm;

import org.springframework.data.repository.CrudRepository;

interface MessageRepository extends CrudRepository<Message, String> {
}
