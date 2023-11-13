import {ICard} from "../../../../model/Card";
export type CardProps = {
    card: ICard,
    deleteCard: (id: number) => void
    updateName: (card: ICard, text: string) => void
    updateDescription: (card: ICard, text: string) => void
}