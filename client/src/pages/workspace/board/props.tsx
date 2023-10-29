import {BoardEntity} from "../../../model/entity/BoardEntity";

export type DeleteBoardProp = {
    board: BoardEntity,
    onDelete: (id: number) => void
}

export type AddBoardProp = {
    onCreate: (board: BoardEntity) => void
}