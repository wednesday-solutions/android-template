package com.wednesday.template.service.room.migration.autoMigrationSpec

import androidx.room.DeleteColumn
import androidx.room.migration.AutoMigrationSpec

@DeleteColumn(
    tableName = "current_weather",
    columnName = "sys_type"
)
@DeleteColumn(
    tableName = "current_weather",
    columnName = "sys_id"
)
class Version1to2MigrationSpec : AutoMigrationSpec